package isika.p3.amappli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.repo.TenancyRepository;

@Service
public class GraphismServiceImpl {

	@Autowired
	private TenancyRepository tenancyRepo;

	public String getMapStyleLightByTenancyId(Long tenancyId) {
		Tenancy tenancy = tenancyRepo.findById(tenancyId).orElse(null);
		String colorPalette = tenancy.getGraphism().getColorPalette().toString();
		if (colorPalette.equals("PALETTE1")) {
			// theme1 white
			return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y";
		} else if (colorPalette.equals("PALETTE2")) {
			// theme2 white
			return "mapbox://styles/tiroirmorgane/cm52ch9kt00ch01r13h25gm01";
		} else if (colorPalette.equals("PALETTE3")) {
			// theme3 white
			return "mapbox://styles/tiroirmorgane/cm52cizgb00c201s94zpr3c3w";
		} else if (colorPalette.equals("PALETTE4")) {
			// theme4 white
			return "mapbox://styles/tiroirmorgane/cm52cjqtu002z01sa8typ15f6";
		} else if (colorPalette.equals("PALETTE5")) {
			// theme5 white
			return "mapbox://styles/tiroirmorgane/cm52claak00dh01qyb016bm90";
		} else if (colorPalette.equals("PALETTE6")) {
			// theme6 white
			return "mapbox://styles/tiroirmorgane/cm52dmxt500c401s9ckayancx";
		}
		// them 6 white style by default
		return "mapbox://styles/tiroirmorgane/cm52dmxt500c401s9ckayancx";
	}
	
	public String getMapStyleDarkByTenancyId(Long tenancyId) {
		Tenancy tenancy = tenancyRepo.findById(tenancyId).orElse(null);
		String colorPalette = tenancy.getGraphism().getColorPalette().toString();
		if (colorPalette.equals("PALETTE1")) {
			// theme1 dark
			return "mapbox://styles/tiroirmorgane/cm52cqefg003101sa878udky6";
		} else if (colorPalette.equals("PALETTE2")) {
			// theme2 dark
			return "mapbox://styles/tiroirmorgane/cm52cuoxd00c101sa4gdl1bye";
		} else if (colorPalette.equals("PALETTE3")) {
			// theme3 dark
			return "mapbox://styles/tiroirmorgane/cm52cw61a00cl01qohw2ifo3i";
		} else if (colorPalette.equals("PALETTE4")) {
			// theme4 dark
			return "mapbox://styles/tiroirmorgane/cm52cxc4o00cm01qo3eei2j9x";
		} else if (colorPalette.equals("PALETTE5")) {
			// theme5 dark
			return "mapbox://styles/tiroirmorgane/cm52cyg8t00c301s95paxgbyb";
		} else if (colorPalette.equals("PALETTE6")) {
			// theme6 dark
			return "mapbox://styles/tiroirmorgane/cm52czmoi00ci01r1g3po4h2b";
		}
		// them 6 white style by default
		return "mapbox://styles/tiroirmorgane/cm52dmxt500c401s9ckayancx";
	}

	public Tenancy getTenancyById(Long tenancyId) {
		return tenancyRepo.findById(tenancyId).orElse(null);
	}

	public String getColorPaletteByTenancyId(Long tenancyId) {
		Tenancy tenancy = tenancyRepo.findById(tenancyId).orElse(null);
		String colorPalette = tenancy.getGraphism().getColorPalette().toString();
		if (colorPalette.equals("PALETTE1")) {
			return "theme-1";
		} else if (colorPalette.equals("PALETTE2")) {
			return "theme-2";
		} else if (colorPalette.equals("PALETTE3")) {
			return "theme-3";
		} else if (colorPalette.equals("PALETTE4")) {
			return "theme-4";
		} else if (colorPalette.equals("PALETTE5")) {
			return "theme-5";
		} else if (colorPalette.equals("PALETTE6")) {
			return "theme-6";
		}
		return "theme-1";
	}
}
