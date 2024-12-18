package isika.p3.amappli.service;

import java.util.List;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.User;

public interface TenancyService {
	
	  Tenancy createTenancy(Tenancy tenancy);
	    List<Tenancy> getAllTenancies();
	    Tenancy getTenancyById(Long id);
	    void deleteTenancy(Long id);
		void addTestTenancies();
		HomePageContent getHomePageContentByTenancyId(Long id);
	

}
