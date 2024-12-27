package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.dto.SupplierDTO;
import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.user.User;

public interface AmapAdminUserService {

	User addTenancyUser(UserDTO supplierDTO, Long tenancyId);
	User addTenancySupplier(SupplierDTO supplierDTO, Long tenancyId);
	void generateUsers(Long tenancyId);
	User findById(Long userId);
	User saveUser(User user);
	void hideUser(User user);
	void hideById(Long userId);
	List<User> findAll(Long tenancyId);
	List<User> findSuppliers(Long tenancyId);
	
}
