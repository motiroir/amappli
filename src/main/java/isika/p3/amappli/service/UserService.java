package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.dto.NewUserDTO;
import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.User;

public interface UserService {

	void addPlatformUser(NewUserDTO newUserDTO);
	User addTenancyUser(UserDTO userDTO, Long tenancyId);
	void generateUsers();
	User saveUser(User user);
	User findById(Long userId);
	List<User> findAll();
	User authenticateUser(String email, String password);
	boolean existsByEmailAndTenancy(String email, Tenancy tenancy);
}
