package isika.p3.amappli.repo.amap;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.auth.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	 Role findByName(String name);

}
