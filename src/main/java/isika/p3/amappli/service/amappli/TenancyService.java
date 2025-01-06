package isika.p3.amappli.service.amappli;

import java.io.IOException;
import java.util.List;

import isika.p3.amappli.dto.amappli.NewTenancyDTO;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;

public interface TenancyService {
	
	
	void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO);
	Tenancy createTenancy(Tenancy tenancy);
	List<Tenancy> getAllTenancies();
	Tenancy getTenancyById(Long id);
	Tenancy getTenancyByAlias(String alias);
	void deleteTenancy(Long id);
	
	HomePageContent getHomePageContentByTenancyId(Long id);
	HomePageContent getHomePageContentByTenancyAlias(String alias);


}
