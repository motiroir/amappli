package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.dto.SupplierDTO;
import isika.p3.amappli.entities.user.User;

public interface AmapAdminUserService {

	User addTenancyUser(SupplierDTO supplierDTO, Long tenancyId);
	void generateUsers();
	User findById(Long userId);
	User saveUser(User user);
	void hideUser(User user);
	void hideById(Long userId);
	List<User> findAll(Long tenancyId);
	List<User> findSuppliers(Long tenancyId);
	
}
