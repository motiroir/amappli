package isika.p3.amappli.service.amap;

import java.util.List;

import isika.p3.amappli.dto.amap.SupplierDTO;
import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.entities.user.User;

public interface AmapAdminUserService {

	User addTenancyUser(UserDTO supplierDTO, String tenancyAlias);
	User addTenancySupplier(SupplierDTO supplierDTO, String tenancyAlias);
	void generateUsers(String tenancyAlias);
	User findById(Long userId);
	User saveUser(User user);
	void hideUser(User user);
	void hideById(Long userId);
	List<User> findAll(Long tenancyId);
	List<User> findAll(String tenancyAlias);
	List<User> findSuppliers(Long tenancyId);
	List<User> findSuppliers(String tenancyAlias);
	
}
