package isika.p3.amappli.service.amappli;

import java.util.List;

import isika.p3.amappli.dto.amap.TenancyUpdateAddressDTO;
import isika.p3.amappli.dto.amap.TenancyUpdateLogo;
import isika.p3.amappli.dto.amap.TenancyUpdateNameAliasDTO;
import isika.p3.amappli.dto.amap.TenancyUpdateSloganDTO;
import isika.p3.amappli.dto.amappli.NewTenancyDTO;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;

public interface TenancyService {
	
	
	void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO, Long creatorUserId);
	Tenancy createTenancy(Tenancy tenancy);
	List<Tenancy> getAllTenancies();
	Tenancy getTenancyById(Long id);
	Tenancy getTenancyByAlias(String alias);
	void deleteTenancy(Long id);
	void updateTenancyNameOrAlias(TenancyUpdateNameAliasDTO updateInfo, String alias);
	void updateTenancySlogan(TenancyUpdateSloganDTO updateInfo, String alias);
	void updateTenancyLogo(TenancyUpdateLogo updateInfo, String alias);
	void updateTenancyAddress(TenancyUpdateAddressDTO updateInfo, String alias);
	
	HomePageContent getHomePageContentByTenancyId(Long id);
	HomePageContent getHomePageContentByTenancyAlias(String alias);


}
