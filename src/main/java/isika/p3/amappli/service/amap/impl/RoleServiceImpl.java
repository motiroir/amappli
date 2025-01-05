package isika.p3.amappli.service.amap.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import isika.p3.amappli.dto.amappli.RoleDTO;
import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.repo.amap.RoleRepository;
import isika.p3.amappli.repo.amappli.PermissionRepository;
import isika.p3.amappli.service.amap.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	private final PermissionRepository permissionRepository;

	public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	@Override
	public Role findById(Long roleId) {
		return roleRepository.findById(roleId).orElse(null);
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

	@Override
	public void manageRoleFromRoleManagmentPage(RoleDTO roleDTO) {
		Role r = null;
		if (roleDTO.getRoleId() != null) {
			Optional<Role> oR = roleRepository.findById(roleDTO.getRoleId());
			// update if existing role
			if (oR.isPresent()) {
				r = oR.get();
			}
			// create new role
			else {
				r = new Role();
			}
		}
		else {
			r = new Role();
		}
		r.setName(roleDTO.getRoleName());
		List<Permission> permissionsList = new ArrayList<>();
		permissionRepository.findAllById(roleDTO.getPermissions()).forEach(permissionsList::add);

		Set<Permission> permissions = new HashSet<>(permissionsList);
		r.setPermissions(permissions);
		roleRepository.save(r);
	}

}
