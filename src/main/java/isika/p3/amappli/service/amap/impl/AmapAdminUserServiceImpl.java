package isika.p3.amappli.service.amap.impl;

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

import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.CompanyDetails;
import isika.p3.amappli.entities.user.ContactInfo;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.exceptions.TenancyNotFoundException;
import isika.p3.amappli.dto.amap.SupplierDTO;
import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.service.amap.AddressService;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.CompanyDetailsService;
import isika.p3.amappli.service.amap.ContactInfoService;
import isika.p3.amappli.service.amap.RoleService;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;
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
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		this.tenancyService = tenancyService;
		this.roleService = roleService;
	}
    
    
	@Override
	public User addTenancyUser(UserDTO userDTO, String tenancyAlias) {
        // Vérifiez si la tenancy existe
        Tenancy tenancy = tenancyService.getTenancyByAlias(tenancyAlias);
        if (tenancy == null) {
            throw new TenancyNotFoundException("Tenancy introuvable avec l'Alias : " + tenancyAlias);
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
	public User addTenancySupplier(SupplierDTO supplierDTO, String tenancyAlias) {
		// Vérifiez si la tenancy existe
		Tenancy tenancy = tenancyService.getTenancyByAlias(tenancyAlias);
		if (tenancy == null) {
			throw new TenancyNotFoundException("Tenancy introuvable avec l'Alias : " + tenancyAlias);
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
	public void generateUsers(String tenancyAlias) {
		
		roleService.addtestRoles();
		Tenancy tenancy = tenancyService.getTenancyByAlias(tenancyAlias);
        Faker faker = new Faker(new Locale("fr-FR"));
        for(int i=0;i < 20 ;i++){
        	User u = new User();
        	u.setEmail(faker.internet().emailAddress());
        	u.setPassword(faker.internet().password());
        	u.setCreditBalance(BigDecimal.ZERO);
        	u.setActive(true);
        	u.setTenancy(tenancy);
        	saveUser(u);
        	
            Address a = Address.builder()
                .line1(faker.address().buildingNumber())
                .line2(faker.address().streetName())
                .postCode("44190")
                .city(faker.address().cityName())
				.user(u)
                .build();
            this.addressService.save(a);
            ContactInfo cI = ContactInfo.builder()
                .name(faker.name().lastName())
                .firstName(faker.name().firstName())
                .phoneNumber("0102030405")
				.user(u)
                .build();
            this.contactInfoService.save(cI);
            CompanyDetails cD = CompanyDetails.builder()
        		.companyName(faker.company().name())
				.siretNumber(Long.toString(faker.number().randomNumber(14, true)))
				.user(u)
				.build();
            this.companyDetailsService.save(cD);
            Set<Role> roles = new HashSet<>();
            Role member = roleService.findByName("MEMBER USER");
            Role supplier = roleService.findByName("SUPPLIER");
            roles.add(i%2 == 0 ? member : supplier);
            
            u.setAddress(a);
            u.setContactInfo(cI);
            u.setCompanyDetails(cD);
            
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
				&& u.isActive())
				.sorted((u1,u2)->u2.getUserId().compareTo(u1.getUserId()))
				.toList();
	}

	@Override
	public List<User> findAll(String tenancyAlias) {
		return ((List<User>) userService.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyAlias().equals(tenancyAlias)
						&& u.isActive())
				.sorted((u1,u2)->u2.getUserId().compareTo(u1.getUserId()))
				.toList();
	}

	@Override
	public List<User> findSuppliers(String tenancyAlias) {
		return ((List<User>) userService.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyAlias().equals(tenancyAlias) 
						&& u.getRoles().contains(roleService.findByName("SUPPLIER"))
						&& u.isActive())
				.sorted((u1,u2)->u2.getUserId().compareTo(u1.getUserId()))
				.toList();
	}

	@Override
	public List<User> findSuppliers(Long tenancyId) {
		return ((List<User>) userService.findAll()).stream()
				.filter(u -> u.getTenancy().getTenancyId() == tenancyId 
						&& u.getRoles().contains(roleService.findByName("SUPPLIER"))
						&& u.isActive()
						&& u.getCompanyDetails() != null
						&& u.getCompanyDetails().getCompanyName() != null)
				.sorted((u1,u2)->u2.getUserId().compareTo(u1.getUserId()))
				.toList();
	}

	@Override
	public User findById(Long userId) {
		return userService.findById(userId);
	}


	@Override
	public User updateUser(SupplierDTO updatedUserDTO) {
		User oldUser = this.findById(updatedUserDTO.getUserId());
		if (oldUser == null) {
			throw new IllegalArgumentException("Le user avec l'ID " + updatedUserDTO.getUserId() + " n'existe pas.");
		}
		
		oldUser.getAddress().setLine1((updatedUserDTO.getLine1()));
		oldUser.getAddress().setLine2((updatedUserDTO.getLine2()));
		oldUser.getAddress().setPostCode((updatedUserDTO.getPostCode()));
		oldUser.getAddress().setCity((updatedUserDTO.getCity()));
		oldUser.getContactInfo().setName((updatedUserDTO.getName()));
		oldUser.getContactInfo().setFirstName((updatedUserDTO.getFirstName()));
		oldUser.getContactInfo().setPhoneNumber((updatedUserDTO.getPhoneNumber()));
		oldUser.setCreditBalance(updatedUserDTO.getCreditBalance());
		oldUser.setEmail(updatedUserDTO.getEmail());
		oldUser.setCreditBalance(updatedUserDTO.getCreditBalance());
		oldUser.setRoles(new HashSet<Role>(updatedUserDTO.getRoles()));
		
		this.saveUser(oldUser);
		
		return oldUser;
	}

}
