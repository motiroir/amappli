package isika.p3.amappli.service.amap.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import isika.p3.amappli.dto.amap.NewUserDTO;
import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.exceptions.TenancyNotFoundException;
import isika.p3.amappli.repo.amap.UserRepository;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;
import jakarta.transaction.Transactional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TenancyService tenancyService;

    public UserServiceImpl(UserRepository userRepository, TenancyService tenancyService) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.tenancyService = tenancyService;
    }
    
    @Transactional
    public User addTenancyUser(UserDTO userDTO, Long tenancyId) {
        // Vérifiez si la tenancy existe
        Tenancy tenancy = tenancyService.getTenancyById(tenancyId);
        if (tenancy == null) {
            throw new TenancyNotFoundException("Tenancy introuvable avec l'ID : " + tenancyId);
        }
        // Vérifiez si l'email existe déjà pour cette tenancy
        if (this.existsByEmailAndTenancy(userDTO.getEmail(), tenancy)) {
            throw new EmailAlreadyExistsException("Un utilisateur avec cet email existe déjà pour cette AMAP.");
        }

        // Convertir UserDTO en entité User
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setCreditBalance(userDTO.getCreditBalance() != null ? userDTO.getCreditBalance() : BigDecimal.ZERO);
        user.setAddress(userDTO.getAddress()); // Si address est déjà une entité
        user.setContactInfo(userDTO.getContactInfo()); // Idem pour contactInfo

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Associer l'utilisateur à la tenancy
        user.setTenancy(tenancy);
        user.setActive(true); // Activation par défaut

        // Sauvegarder l'utilisateur
        return userRepository.save(user);
    }
    
    public User authenticateUser(String email, String password) throws RuntimeException {
        
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Email ou mot de passe incorrect.");
        }
    }

    @Transactional
    public void addPlatformUser(NewUserDTO newUserDTO) throws EmailAlreadyExistsException{
        try {
            // Get info from DTO into new User
            User user = new User();
            BeanUtils.copyProperties(newUserDTO,user);
            // Nested properties are not copied by BeanUtils
            user.setAddress(newUserDTO.getAddress());
            user.setContactInfo(newUserDTO.getContactInfo());

            // At first, the platform user has no tenancy space
            user.setTenancy(null);
            // The user is active, he's only deactivated if there's a problem
            user.setActive(true);
            // The user has 0 credits by default
            user.setCreditBalance(new BigDecimal(0));
            saveUser(user); //encoding done inside
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
            
            saveUser(u);
        }

    }

    @Override
    public boolean existsByEmailAndTenancy(String email, Tenancy tenancy) {
    	return this.userRepository.existsByEmailAndTenancy(email, tenancy);
    }
    
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public List<User> findAll() {
    	return (List<User>) userRepository.findAll();
    }

	@Override
	public User findById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
