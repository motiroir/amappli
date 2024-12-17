package isika.p3.amappli.service;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
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
