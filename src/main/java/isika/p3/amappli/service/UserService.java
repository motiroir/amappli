package isika.p3.amappli.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.user.User;

public interface UserService extends UserDetailsService {

	void addPlatformUser(User user);
	User addTenancyUser(UserDTO userDTO, Long tenancyId);
	void generateUsers();
	void saveUser(User user);
	User authenticateUser(String email, String password);
}
