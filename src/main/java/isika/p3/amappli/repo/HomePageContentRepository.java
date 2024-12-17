package isika.p3.amappli.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.tenancy.HomePageContent;

@Repository
public interface HomePageContentRepository extends CrudRepository<HomePageContent, Long>{

}
