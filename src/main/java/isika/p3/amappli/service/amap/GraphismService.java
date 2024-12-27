package isika.p3.amappli.service.amap;

import isika.p3.amappli.entities.tenancy.Tenancy;

public interface GraphismService {

	String getMapStyleLightByTenancyId(Long tenancyId);
	String getMapStyleDarkByTenancyId(Long tenancyId);
	Tenancy getTenancyById(Long tenancyId);
	String getColorPaletteByTenancyId(Long tenancyId);
	String getFontByTenancyId(Long tenancyId);
	
}
