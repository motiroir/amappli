package isika.p3.amappli.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.dto.SupplierDTO;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.CompanyDetails;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.exceptions.TenancyNotFoundException;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.repo.AddressRepository;
import isika.p3.amappli.repo.CompanyDetailsRepository;
import isika.p3.amappli.repo.ContactInfoRepository;
import isika.p3.amappli.repo.TenancyRepository;
import isika.p3.amappli.repo.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AmapAdminUserServiceImpl implements AmapAdminUserService {

	@Autowired
	private final UserService userService;
	
	@Autowired
	private final AddressService addressService;
	
	@Autowired
	private final ContactInfoService contactInfoService;
	
	@Autowired
	private final CompanyDetailsService companyDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TenancyService tenancyService;
    
    @Autowired
    private final RoleService roleService;
    
	public AmapAdminUserServiceImpl(UserService userService, AddressService addressService,
			ContactInfoService contactInfoService, CompanyDetailsService companyDetailsService,
			TenancyService tenancyService, RoleService roleService) {
		this.userService = userService;
		this.addressService = addressService;
		this.contactInfoService = contactInfoService;
		this.companyDetailsService = companyDetailsService;
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;
		this.tenancyService = tenancyService;
		this.roleService = roleService;
	}
    
    
	@Override
	public User addTenancyUser(UserDTO userDTO, Long tenancyId) {
        // Vérifiez si la tenancy existe
        Tenancy tenancy = tenancyService.getTenancyById(tenancyId);
        if (tenancy == null) {
            throw new TenancyNotFoundException("Tenancy introuvable avec l'ID : " + tenancyId);
        }
        // Vérifiez si l'email existe déjà pour cette tenancy
        if (userService.existsByEmailAndTenancy(userDTO.getEmail(), tenancy)) {
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
        return userService.saveUser(user);
    }
	
	@Override
	public User addTenancySupplier(SupplierDTO supplierDTO, Long tenancyId) {
		// Vérifiez si la tenancy existe
		Tenancy tenancy = tenancyService.getTenancyById(tenancyId);
		if (tenancy == null) {
			throw new TenancyNotFoundException("Tenancy introuvable avec l'ID : " + tenancyId);
		}
		// Vérifiez si l'email existe déjà pour cette tenancy
		if (userService.existsByEmailAndTenancy(supplierDTO.getEmail(), tenancy)) {
			throw new EmailAlreadyExistsException("Un utilisateur avec cet email existe déjà pour cette AMAP.");
		}
		Address address = new Address();
		address.setLine1(supplierDTO.getLine1());
		address.setLine2(supplierDTO.getLine2());
		address.setPostCode(supplierDTO.getPostCode());
		address.setCity(supplierDTO.getCity());
		address = this.addressService.save(address);
		
		CompanyDetails cd = new CompanyDetails();
		cd.setCompanyName(supplierDTO.getCompanyName());
		cd.setSiretNumber(supplierDTO.getSiretNumber());
		cd = this.companyDetailsService.save(cd);
		
		ContactInfo ci = new ContactInfo();
		ci.setFirstName(supplierDTO.getFirstName());
		ci.setName(supplierDTO.getName());
		ci.setPhoneNumber(supplierDTO.getPhoneNumber());
		ci = this.contactInfoService.save(ci);
		
		// Convertir UserDTO en entité User
		User user = new User();
		user.setEmail(supplierDTO.getEmail());
		user.setCreditBalance(BigDecimal.ZERO);
		user.setAddress(address);
		user.setContactInfo(ci);
		user.setCompanyDetails(cd);
		
		// Encoder le mot de passe
		user.setPassword(passwordEncoder.encode("Producteur"));
		
		// Associer l'utilisateur à la tenancy
		user.setTenancy(tenancy);
		user.setActive(true); // Activation par défaut
		
		// Sauvegarder l'utilisateur
		return userService.saveUser(user);
	}

	@Override
	@Transactional
	public void generateUsers(Long tenancyId) {
		
		roleService.addtestRoles();
		Tenancy tenancy = tenancyService.getTenancyById(tenancyId);

        Faker faker = new Faker(new Locale("fr-FR"));
        for(int i=0;i < 20 ;i++){
            Address a = Address.builder()
                .line1(faker.address().buildingNumber())
                .line2(faker.address().streetName())
                .postCode("44190")
                .city(faker.address().cityName())
                .build();
            this.addressService.save(a);

            ContactInfo cI = ContactInfo.builder()
                .name(faker.name().lastName())
                .firstName(faker.name().firstName())
                .phoneNumber("0102030405")
                .build();
            this.contactInfoService.save(cI);
            
            CompanyDetails cD = CompanyDetails.builder()
        		.companyName(faker.company().name())
				.siretNumber(Long.toString(faker.number().randomNumber(14, true)))
				.build();
            this.companyDetailsService.save(cD);
            
            Set<Role> roles = new HashSet<>();
            
            Role member = roleService.findByName("MEMBER USER");
            Role supplier = roleService.findByName("SUPPLIER");
            
            roles.add(i%2 == 0 ? member : supplier);

            User u = User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .address(a)
                .contactInfo(cI)
                .creditBalance(BigDecimal.ZERO)
                .isActive(true)
                .companyDetails(cD)
                .tenancy(tenancy)
                .build();
            
            u.setRoles(roles);
            saveUser(u);
        }
	}

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return user;
    }

	@Override
	public void hideUser(User user) {
		user.setActive(false);
		userService.saveUser(user);
	}

	@Override
	public void hideById(Long userId) {
		this.hideUser(this.findById(userId));
	}

	@Override
	public List<User> findAll(Long tenancyId) {
		return ((List<User>) userService.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyId() == tenancyId
				&& u.isActive()).toList();
	}

	@Override
	public List<User> findSuppliers(Long tenancyId) {
		return ((List<User>) userService.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyId() == tenancyId 
						&& u.getRoles().contains(roleService.findByName("SUPPLIER"))
						&& u.isActive()).toList();
	}

	@Override
	public User findById(Long userId) {
		return userService.findById(userId);
	}

}
