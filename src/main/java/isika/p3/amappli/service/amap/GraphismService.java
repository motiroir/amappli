package isika.p3.amappli.service.amap;

import org.springframework.ui.Model;

import isika.p3.amappli.entities.tenancy.Tenancy;

public interface GraphismService {

	String getMapStyleLightByTenancyAlias(String alias);

	String getMapStyleDarkByTenancyAlias(String alias);

	Tenancy getTenancyByAlias(String alias);

	String getColorPaletteByTenancyAlias(String alias);

	String getFontByTenancyAlias(String alias);

	String getLatitudeByTenancyAlias(String alias);

	String getLongitudeByTenancyAlias(String alias);
	
	Long getUserIdFromContext();
	
	void addGraphismAttributes(String alias, Model model);

}
