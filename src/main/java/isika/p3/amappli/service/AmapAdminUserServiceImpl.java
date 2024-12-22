package isika.p3.amappli.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;

import isika.p3.amappli.dto.SupplierDTO;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.CompanyDetails;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.exceptions.TenancyNotFoundException;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.repo.UserRepository;

public class AmapAdminUserServiceImpl implements AmapAdminUserService {

	private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TenancyService tenancyService;
    
    private final RoleService roleService;

    public AmapAdminUserServiceImpl(UserRepository userRepository, TenancyService tenancyService, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.tenancyService = tenancyService;
        this.roleService = roleService;
    }
    
    
	@Override
	public User addTenancyUser(SupplierDTO supplierDTO, Long tenancyId) {
        // Vérifiez si la tenancy existe
        Tenancy tenancy = tenancyService.getTenancyById(tenancyId);
        if (tenancy == null) {
            throw new TenancyNotFoundException("Tenancy introuvable avec l'ID : " + tenancyId);
        }
        // Vérifiez si l'email existe déjà pour cette tenancy
        if (userRepository.existsByEmailAndTenancy(supplierDTO.getEmail(), tenancy)) {
            throw new EmailAlreadyExistsException("Un utilisateur avec cet email existe déjà pour cette AMAP.");
        }

        // Convertir UserDTO en entité User
        User user = new User();
        user.setEmail(supplierDTO.getEmail());
        user.setCreditBalance(supplierDTO.getCreditBalance() != null ? supplierDTO.getCreditBalance() : BigDecimal.ZERO);
        user.setAddress(supplierDTO.getAddress()); // Si address est déjà une entité
        user.setContactInfo(supplierDTO.getContactInfo()); // Idem pour contactInfo

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(supplierDTO.getPassword()));

        // Associer l'utilisateur à la tenancy
        user.setTenancy(tenancy);
        user.setActive(true); // Activation par défaut

        // Sauvegarder l'utilisateur
        return userRepository.save(user);
    }

	@Override
	public void generateUsers() {
		
		roleService.addtestRoles();

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
            
            CompanyDetails cD = CompanyDetails.builder()
        		.companyName(faker.company().name())
				.siretNumber(Long.toString(faker.number().randomNumber(14, true)))
				.build();
            
            Set<Role> roles = new HashSet<>();
            Role member = roleService.findByName("MEMBER USER");
            Role supplier = roleService.findByName("SUPPLIER");
            if (i%2 == 0) {
				roles.add(member);
			} else {
				roles.add(supplier);
			}
            

            User u = User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .address(a)
                .contactInfo(cI)
                .isActive(true)
                .companyDetails(cD)
                .roles(roles)
                .build();
            
            saveUser(u);
        }
	}

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

	@Override
	public void hideUser(User user) {
		user.setActive(false);

	}

	@Override
	public void hideById(Long userId) {
		this.findById(userId).setActive(false);
	}

	@Override
	public List<User> findAll(Long tenancyId) {
		return ((List<User>) userRepository.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyId() == tenancyId).toList();
	}

	@Override
	public List<User> findSuppliers(Long tenancyId) {
		return ((List<User>) userRepository.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyId() == tenancyId 
						&& u.getRoles().contains(roleService.findByName("ROLE_PROVIDER"))).toList();
	}

	@Override
	public User findById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
