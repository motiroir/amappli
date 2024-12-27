package isika.p3.amappli.service.amappli;

import java.util.List;

import isika.p3.amappli.dto.amappli.NewTenancyDTO;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;

public interface TenancyService {
	
	
	void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO);
	Tenancy createTenancy(Tenancy tenancy);
	List<Tenancy> getAllTenancies();
	Tenancy getTenancyById(Long id);
	void deleteTenancy(Long id);
	void addTestTenancies();
	HomePageContent getHomePageContentByTenancyId(Long id);
	Tenancy getTenancyByAlias(String alias);


}
