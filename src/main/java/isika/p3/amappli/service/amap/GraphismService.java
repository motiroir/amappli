package isika.p3.amappli.service.amap;

import isika.p3.amappli.entities.tenancy.Tenancy;

public interface GraphismService {

	String getMapStyleLightByTenancyAlias(String alias);

	String getMapStyleDarkByTenancyAlias(String alias);

	Tenancy getTenancyByAlias(String alias);

	String getColorPaletteByTenancyAlias(String alias);

	String getFontByTenancyAlias(String alias);

	String getLatitudeByTenancyAlias(String alias);

	String getLongitudeByTenancyAlias(String alias);

}
