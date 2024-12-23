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
			// white style
			return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y";
		} else if (colorPalette.equals("PALETTE2")) {
			// dark style
			return "mapbox://styles/tiroirmorgane/cm4pjeh6h007k01r0fhs20pkd";
		}
		// white style by default
		return "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y";
	}

	public Address getAddressByTenancyId(Long tenancyId) {
		Tenancy tenancy = tenancyRepo.findById(tenancyId).orElse(null);
		return tenancy.getAddress();
	}

	public String getColorPaletteByTenancyId(Long tenancyId) {
		Tenancy tenancy = tenancyRepo.findById(tenancyId).orElse(null);
		String colorPalette = tenancy.getGraphism().getColorPalette().toString();
		if (colorPalette.equals("PALETTE1")) {
			return "theme-1 light";
		} else if (colorPalette.equals("PALETTE2")) {
			return "theme-1 dark";
		}
		return "theme-1 light";
	}
}
