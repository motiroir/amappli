package isika.p3.amappli.service.amap.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.repo.amap.RoleRepository;
import isika.p3.amappli.service.amap.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role createRole(Role role) {
		if (roleRepository.findByName(role.getName()) != null) {
			throw new IllegalArgumentException("Ce rôle existe déjà !");
		}
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleRepository.deleteById(roleId);

	}

	@Override
	public void addtestRoles() {

		createRoleIfNotExists("ADMIN");
		createRoleIfNotExists("MEMBER USER");
		createRoleIfNotExists("SUPPLIER");

	}

	private void createRoleIfNotExists(String roleName) {
		if (roleRepository.findByName(roleName) == null) {
			Role role = Role.builder().name(roleName).build();
			roleRepository.save(role);
		}
	}

}
