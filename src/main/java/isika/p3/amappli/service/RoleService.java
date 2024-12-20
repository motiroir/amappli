package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.entities.auth.Role;

public interface RoleService {

	Role findByName(String name);

	List<Role> findAllRoles();

	Role createRole(Role role);

	void deleteRole(Long roleId);

	
    void addtestRoles(); 
}
