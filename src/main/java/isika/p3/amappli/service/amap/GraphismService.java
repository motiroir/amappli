package isika.p3.amappli.service.amap;

import org.springframework.ui.Model;

import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.security.CustomUserDetails;

public interface GraphismService {

	Tenancy getTenancyByAlias(String alias);
	
//	String getMapStyleLightByTenancyAlias(Tenancy tenancy);
//
//	String getMapStyleDarkByTenancyAlias(Tenancy tenancy);
//
//	String getColorPaletteByTenancyAlias(Tenancy tenancy);
//
//	String getFontByTenancyAlias(Tenancy tenancy);
//
//	String getLatitudeByTenancyAlias(Tenancy tenancy);
//
//	String getLongitudeByTenancyAlias(Tenancy tenancy);
	
	void setUpModel(String alias, Model model);
	
	Long getUserIdFromContext();

	void getUserInfoFromContext(Model model);
	
}
