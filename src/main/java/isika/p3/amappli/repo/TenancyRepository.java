package isika.p3.amappli.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.tenancy.Tenancy;

@Repository
public interface TenancyRepository extends CrudRepository<Tenancy, Long> {

}
