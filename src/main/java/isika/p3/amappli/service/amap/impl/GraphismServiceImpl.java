package isika.p3.amappli.service.amap.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.GraphismService;

@Service
public class GraphismServiceImpl implements GraphismService {

	@Autowired
	private TenancyRepository tenancyRepo;

	public void  addGraphismAttributes(String alias, Model model) {
		// get map style and coordinates depending on tenancy
		model.addAttribute("mapStyleLight", getMapStyleLightByTenancyAlias(alias));
		model.addAttribute("mapStyleDark", getMapStyleDarkByTenancyAlias(alias));
		model.addAttribute("latitude", getLatitudeByTenancyAlias(alias));
		model.addAttribute("longitude", getLongitudeByTenancyAlias(alias));
		// get tenancy info for header footer
		model.addAttribute("tenancy", getTenancyByAlias(alias));
		// get color palette
		model.addAttribute("cssStyle", getColorPaletteByTenancyAlias(alias));
		// get font choice
		model.addAttribute("font", getFontByTenancyAlias(alias));
	}
	
	public Long getUserIdFromContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		//condition for when we don't want to login at each dev iteration
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails loggedUserInfo = (CustomUserDetails) principal;
			return (Long) loggedUserInfo.getAdditionalInfoByKey("userId");
		} else return 1L;
	}
	
	@Override
	public String getMapStyleLightByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y"; // theme1 white by default
		}

		String colorPalette = tenancy.getGraphism().getColorPalette().toString();

		switch (colorPalette) {
		case "PALETTE1":
			return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y"; // theme1 white
		case "PALETTE2":
			return "mapbox://styles/tiroirmorgane/cm52ch9kt00ch01r13h25gm01"; // theme2 white
		case "PALETTE3":
			return "mapbox://styles/tiroirmorgane/cm52cizgb00c201s94zpr3c3w"; // theme3 white
		case "PALETTE4":
			return "mapbox://styles/tiroirmorgane/cm52cjqtu002z01sa8typ15f6"; // theme4 white
		case "PALETTE5":
			return "mapbox://styles/tiroirmorgane/cm52claak00dh01qyb016bm90"; // theme5 white
		case "PALETTE6":
			return "mapbox://styles/tiroirmorgane/cm52dmxt500c401s9ckayancx"; // theme6 white
		default:
			return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y"; // theme1 white by default
		}
	}

	@Override
	public String getMapStyleDarkByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "mapbox://styles/tiroirmorgane/cm52cqefg003101sa878udky6"; // theme1 dark by default
		}

		String colorPalette = tenancy.getGraphism().getColorPalette().toString();

		switch (colorPalette) {
		case "PALETTE1":
			return "mapbox://styles/tiroirmorgane/cm52cqefg003101sa878udky6"; // theme1 dark
		case "PALETTE2":
			return "mapbox://styles/tiroirmorgane/cm52cuoxd00c101sa4gdl1bye"; // theme2 dark
		case "PALETTE3":
			return "mapbox://styles/tiroirmorgane/cm52cw61a00cl01qohw2ifo3i"; // theme3 dark
		case "PALETTE4":
			return "mapbox://styles/tiroirmorgane/cm52cxc4o00cm01qo3eei2j9x"; // theme4 dark
		case "PALETTE5":
			return "mapbox://styles/tiroirmorgane/cm52cyg8t00c301s95paxgbyb"; // theme5 dark
		case "PALETTE6":
			return "mapbox://styles/tiroirmorgane/cm52czmoi00ci01r1g3po4h2b"; // theme6 dark
		default:
			return "mapbox://styles/tiroirmorgane/cm52cqefg003101sa878udky6"; // theme1 dark by default
		}
	}

	@Override
	public Tenancy getTenancyByAlias(String alias) {
		return tenancyRepo.findByTenancyAlias(alias).orElse(null);
	}

	@Override
	public String getColorPaletteByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "theme-1";
		}

		String colorPalette = tenancy.getGraphism().getColorPalette().toString();

		switch (colorPalette) {
		case "PALETTE1":
			return "theme-1";
		case "PALETTE2":
			return "theme-2";
		case "PALETTE3":
			return "theme-3";
		case "PALETTE4":
			return "theme-4";
		case "PALETTE5":
			return "theme-5";
		case "PALETTE6":
			return "theme-6";
		default:
			return "theme-1";
		}
	}

	@Override
	public String getFontByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "futura";
		}
		
		String fontChoice = tenancy.getGraphism().getFontChoice().toString();
		switch (fontChoice) {
		case "FUTURA":
			return "futura";
		case "NUNITO":
			return "nunito-sans";
		case "AUDIOWIDE":
			return "audiowide";
		case "GRENZE":
			return "grenze";
		case "CAVEAT":
			return "caveat";
		case "LIMELIGHT":
			return "limelight";
		default:
			return "futura";
		}
	}
	
	public String getLatitudeByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "42.1880896";
		} else return tenancy.getTenancyLatitude();
	}
	
	public String getLongitudeByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepo.findByTenancyAlias(alias).orElse(null);
		if (tenancy == null || tenancy.getGraphism() == null) {
			return "9.0684138";
		} else return tenancy.getTenancyLongitude();
	}
	
	
}
