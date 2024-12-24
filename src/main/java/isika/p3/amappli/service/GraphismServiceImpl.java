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

	public String getMapboxStyleByTenancyId(Long tenancyId) {
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
