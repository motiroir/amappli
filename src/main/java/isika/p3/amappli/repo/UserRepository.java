package isika.p3.amappli.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
