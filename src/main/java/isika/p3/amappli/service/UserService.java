package isika.p3.amappli.service;

import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.user.User;

public interface UserService {

	void addPlatformUser(User user);
	User registerUser(UserDTO userDTO, Long tenancyId);
	void generateUsers();
	void saveUser(User user);
	User authenticateUser(String email, String password);
}
