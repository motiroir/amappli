package isika.p3.amappli.service;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;

import isika.p3.amappli.repo.TenancyRepository;
import isika.p3.amappli.repo.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    // @Value("${PASSWORD_SALT}")
    // private String passwordSalt;

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    private TenancyRepository tenancyRepository;
    @Autowired
    private TenancyService tenancyService;
    

    
    @Override
    @Transactional
    public User registerUser(UserDTO userDTO, Long tenancyId) {
        // Vérifiez si la tenancy existe
        Tenancy tenancy = tenancyService.getTenancyById(tenancyId);
        if (tenancy == null) {
            throw new RuntimeException("Tenancy introuvable avec l'ID : " + tenancyId);
        }

        // Vérifiez si l'email existe déjà pour cette tenancy
        if (userRepository.existsByEmailAndTenancy(userDTO.getEmail(), tenancy)) {
            throw new EmailAlreadyExistsException("Un utilisateur avec cet email existe déjà pour cette tenancy.");
        }

        // Convertir UserDTO en entité User
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreditBalance(userDTO.getCreditBalance() != null ? userDTO.getCreditBalance() : BigDecimal.ZERO);
        user.setAddress(userDTO.getAddress()); // Si address est déjà une entité
        user.setContactInfo(userDTO.getContactInfo()); // Idem pour contactInfo

        // Encoder le mot de passe
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Associer l'utilisateur à la tenancy
        user.setTenancy(tenancy);
        user.setActive(true); // Activation par défaut

        // Sauvegarder l'utilisateur
        return userRepository.save(user);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    
    
    public User authenticateUser(String email, String password) throws RuntimeException {
        // Création d'un PasswordEncoder
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Email ou mot de passe incorrect.");
        }
    }


    
    
    
    
    
    
    
    
    @Transactional
    public void addPlatformUser(User user) throws EmailAlreadyExistsException{
        try {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user.setPassword(
                passwordEncoder.encode(user.getPassword())
            );
            // At first, the platform user has no tenancy space
            user.setTenancy(null);
            // The user is active, he's only deactivated if there's a problem
            user.setActive(true);
            // The user has 0 credits by default
            user.setCreditBalance(new BigDecimal(0));
            userRepository.save(user);
        }
        catch (RuntimeException e){
            throw new EmailAlreadyExistsException("Cet email est déjà utilisé.");
        }
        
    }
    

    @Transactional
    public void generateUsers() {

        Faker faker = new Faker(new Locale("fr-FR"));
        for(int i=0;i < 20 ;i++){
            Address a = Address.builder()
                .line1(faker.address().buildingNumber())
                .line2(faker.address().streetName())
                .postCode(faker.address().zipCode())
                .city(faker.address().cityName())
                .build();

            ContactInfo cI = ContactInfo.builder()
                .name(faker.name().lastName())
                .firstName(faker.name().firstName())
                .phoneNumber(faker.phoneNumber().phoneNumber())
                .build();

            User u = User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .address(a)
                .contactInfo(cI)
                .isActive(true)
                .build();
            
            addPlatformUser(u);
        }

    }

}
