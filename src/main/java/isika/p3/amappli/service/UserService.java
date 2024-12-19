package isika.p3.amappli.service;

import isika.p3.amappli.entities.user.User;

public interface UserService {

	void addPlatformUser(User user);
	User registerUser(User user, Long tenancyId);
	void generateUsers();
	void saveUser(User user);
}
