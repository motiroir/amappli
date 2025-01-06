package isika.p3.amappli.service.amappli.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.api.NominatimAPI;
import isika.p3.amappli.dto.amappli.NewTenancyDTO;
import isika.p3.amappli.dto.amappli.ValueDTO;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.Options;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.exceptions.TenancyAliasAlreadyTakenException;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amappli.TenancyService;

@Service
public class TenancyServiceImpl implements TenancyService {


	@Autowired
	private TenancyRepository tenancyRepository;

	@Override
	public HomePageContent getHomePageContentByTenancyId(Long id) {
		Tenancy tenancy = tenancyRepository.findById(id).orElse(null);

		if (tenancy != null) {
			return tenancy.getHomePageContent();
		}

		return null;
	}

	@Override
	public Tenancy createTenancy(Tenancy tenancy) {

		if (tenancy.getHomePageContent() == null) {
			HomePageContent homePageContent = HomePageContent.builder().subTitle("Default Subtitle")
					.showSuppliers(false).tenancy(tenancy).build();
			tenancy.setHomePageContent(homePageContent);
		}

		return tenancyRepository.save(tenancy);
	}

	@Override
	public List<Tenancy> getAllTenancies() {
		return (List<Tenancy>) tenancyRepository.findAll();
	}

	@Override
	public Tenancy getTenancyById(Long id) {
		return tenancyRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteTenancy(Long id) {
		tenancyRepository.deleteById(id);
	}

	// Méthode pour charger une image depuis les ressources internes et la convertir
	// en Base64
	private String loadImageFromResources(String imageName) throws IOException {
		InputStream imageStream = getClass().getClassLoader().getResourceAsStream("image/" + imageName);
		if (imageStream == null) {
			throw new IOException("Image not found in resources: " + imageName);
		}
		byte[] imageBytes = imageStream.readAllBytes();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	
	@Override
	public void createTenancyFromWelcomeForm(NewTenancyDTO newTenancyDTO) {
		Tenancy tenancy = new Tenancy();

		// Name
		tenancy.setTenancyName(newTenancyDTO.getTenancyName());
		// Address
		tenancy.setAddress(newTenancyDTO.getAddress());

		// Compute GPS coordinates from Address
		String coordinates = NominatimAPI.getGPSFromAddress(newTenancyDTO.getAddress());
		if (coordinates != null) {
			String[] coor = coordinates.split(",");
			tenancy.setTenancyLatitude(coor[1]);
			tenancy.setTenancyLongitude(coor[0]);
		}
		// Alias
		tenancy.setTenancyAlias(newTenancyDTO.getTenancyAlias());

		// Slogan
		tenancy.setTenancySlogan(newTenancyDTO.getTenancySlogan());

		// MembershipFee Price
		tenancy.setMembershipFeePrice(newTenancyDTO.getMembershipFeePrice());

		// PickUpSchedule
		tenancy.setPickUpSchedule(newTenancyDTO.getPickUpSchedule());

		// Date created and last modified
		tenancy.setDateCreated(LocalDateTime.now());
		tenancy.setDateLastModified(LocalDateTime.now());

		// Graphism
		Graphism graphism = new Graphism();
		graphism.setColorPalette(newTenancyDTO.getColorPalette());
		graphism.setFontChoice(newTenancyDTO.getFontChoice());
		if (!newTenancyDTO.getLogo().isEmpty()) {
			graphism.setLogoImgType(newTenancyDTO.getLogo().getContentType());
			try {
				byte[] thebytes = newTenancyDTO.getLogo().getBytes();
				graphism.setLogoImg(Base64.getEncoder().encodeToString(thebytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		graphism.setTenancy(tenancy);
		tenancy.setGraphism(graphism);

		// Options
		Options options = new Options();
		if (newTenancyDTO.getOption().equals("option-1")) {
			options.setOption1Active(true);
			options.setOption2Active(false);
			options.setOption3Active(false);
		} else if (newTenancyDTO.getOption().equals("option-2")) {
			options.setOption1Active(true);
			options.setOption2Active(true);
			options.setOption3Active(false);
		} else if (newTenancyDTO.getOption().equals("option-3")) {
			options.setOption1Active(true);
			options.setOption2Active(true);
			options.setOption3Active(true);
		}

		options.setTenancy(tenancy);
		tenancy.setOptions(options);
		// HomePageContent
		HomePageContent homePageContent = new HomePageContent();
		// homePageContent.setTenancy(tenancy);
		// The first block
		ContentBlock cbi = new ContentBlock();
		cbi.setContentTitle(newTenancyDTO.getFirstHomePageTitle());
		cbi.setContentText(newTenancyDTO.getFirstHomePageText());
		if (!newTenancyDTO.getFirstHomePagePic().isEmpty()) {
			cbi.setContentImgName(newTenancyDTO.getFirstHomePagePic().getOriginalFilename());
			cbi.setContentImgTypeMIME(newTenancyDTO.getFirstHomePagePic().getContentType());
			try {
				byte[] thebytes = newTenancyDTO.getFirstHomePagePic().getBytes();
				cbi.setContentImg(Base64.getEncoder().encodeToString(thebytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cbi.setHomePageContent(homePageContent);

		homePageContent.getContents().add(cbi);

		for (ValueDTO v : newTenancyDTO.getValues()) {
			if (v.getName().length() > 0) {
				ContentBlock cb = ContentBlock.builder().isValue(true).contentTitle(v.getName())
						.contentText(v.getDescription()).build();
				if (!v.getFile().isEmpty()) {
					cb.setContentImgName(v.getFile().getOriginalFilename());
					cb.setContentImgTypeMIME(v.getFile().getContentType());
					try {
						byte[] thebytes = v.getFile().getBytes();
						cb.setContentImg(Base64.getEncoder().encodeToString(thebytes));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				cb.setHomePageContent(homePageContent);
				homePageContent.getContents().add(cb);
			}
		}
		tenancy.setHomePageContent(homePageContent);
		homePageContent.setTenancy(tenancy);
		// Will cause error if tenancyAlias already taken
		try {
			tenancyRepository.save(tenancy);
		} catch (RuntimeException e) {
			throw new TenancyAliasAlreadyTakenException("Cette url d'AMAP est déjà utilisée.");
		}
	}

	@Override
	public Tenancy getTenancyByAlias(String alias) {
		System.out.println(tenancyRepository.findByTenancyAlias(alias).get().getTenancyAlias());
		return tenancyRepository.findByTenancyAlias(alias).get();
	}

	@Override
	public HomePageContent getHomePageContentByTenancyAlias(String alias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(alias).orElse(null);

		if (tenancy != null) {
			return tenancy.getHomePageContent();
		}

		return null;
	}
}
