package isika.p3.amappli.repo.amap;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);

	@Query("SELECT DISTINCT p FROM Permission p " +
       "JOIN p.roles r " +
       "JOIN r.users u " +
       "WHERE u.email = :email")
	Set<Permission> findPermissionsByEmail(@Param("email") String email);

	boolean existsByEmailAndTenancy(String email, Tenancy tenancy);

}
