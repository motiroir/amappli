package isika.p3.amappli.controllers.amap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.workshop.Workshop;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.ContractService;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.ProductService;
import isika.p3.amappli.service.amap.WorkshopService;

@Controller
@RequestMapping("/amap/{tenancyAlias}/shop")
public class ShopController {

	private final ContractService contractService;
	private final TenancyRepository tenancyRepository;
	private final ProductService productService;
	private final WorkshopService workshopService;
	private final GraphismService graphismService;

	public ShopController(GraphismService graphismService, ContractService contractService, TenancyRepository tenancyRepository,
			ProductService productService, WorkshopService workshopService) {
		this.contractService = contractService;
		this.tenancyRepository = tenancyRepository;
		this.productService = productService;
		this.workshopService = workshopService;
		this.graphismService = graphismService;
	}

	/**
	 * Displays all shoppable contracts in the shop view.
	 */
	@GetMapping("/contracts")
	public String showAllShoppableContracts(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
	    // Récupération de la tenancy
	    Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
	            .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

	    Long cartId = 1L;
	    model.addAttribute("cartId", cartId);

	    // Récupération des contrats "shoppable"
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    // Comptage des contrats par type
	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    // Ajout des comptages au modèle
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);

	    // Ajout des contrats et autres attributs au modèle
	    model.addAttribute("contracts", contracts);
	    model.addAttribute("tenancyAlias", tenancyAlias);
	    addGraphismAttributes(tenancyAlias, model);

	    return "amap/front/shop-contracts";
	}


	/**
	 * Displays all shoppable products in the shop view.
	 */
	@GetMapping("/products")
	public String showAllShoppableProducts(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		Long cartId = 1L;
		model.addAttribute("cartId", cartId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
	    // Conversion des produits
	    List<Map<String, Object>> formattedProducts = productService.findShoppableProductsByTenancy(tenancy)
	        .stream()
	        .map(product -> {
	            Map<String, Object> productData = new HashMap<>();
	            productData.put("productName", product.getProductName());
	            productData.put("productDescription", product.getProductDescription());
	            productData.put("productPrice", product.getProductPrice());
	            productData.put("imageData", product.getImageData());
	            productData.put("imageType", product.getImageType());
	            productData.put("id", product.getId());
	            // Formatage de la date
	            if (product.getExpirationDate() != null) {
	                productData.put("expirationDate", product.getExpirationDate().format(formatter));
	            }
	            return productData;
	        })
	        .toList();
//		List<Product> products = productService.findShoppableProductsByTenancy(tenancy);
	    
	    // Récupération des contrats "shoppable"
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    // Comptage des contrats par type
	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();
		model.addAttribute("products", formattedProducts);
	    // Ajout des comptages au modèle
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
//		model.addAttribute("products", products);
		model.addAttribute("tenancyAlias", tenancyAlias);
		addGraphismAttributes(tenancyAlias, model);

		return "amap/front/shop-products";
	}

	/**
	 * Displays all shoppable products in the shop view.
	 */
	@GetMapping("/workshops")
	public String showAllShoppableWorkshops(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		Long cartId = 1L;
		model.addAttribute("cartId", cartId);
		
	    // Formatter pour LocalDateTime
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("'Le' dd MMMM yyyy 'à' HH:mm", Locale.FRENCH);

	    // Transformation des workshops
	    List<Map<String, Object>> formattedWorkshops = workshopService.findShoppableWorkshopsByTenancy(tenancy)
	        .stream()
	        .map(workshop -> {
	            Map<String, Object> workshopData = new HashMap<>();
	            workshopData.put("workshopName", workshop.getWorkshopName());
	            workshopData.put("workshopDescription", workshop.getWorkshopDescription());
	            workshopData.put("workshopPrice", workshop.getWorkshopPrice());
	            workshopData.put("imageData", workshop.getImageData());
	            workshopData.put("workshopDuration",workshop.getWorkshopDuration());
	            workshopData.put("imageType", workshop.getImageType());
	            workshopData.put("id",workshop.getId());
	            // Formatage de la date et heure
	            if (workshop.getWorkshopDateTime() != null) {
	                workshopData.put("workshopDateTime", workshop.getWorkshopDateTime().format(dateTimeFormatter));
	            }
	            return workshopData;
	        })
	        .toList();

//		List<Workshop> workshops = workshopService.findShoppableWorkshopsByTenancy(tenancy);
//		model.addAttribute("workshops", workshops);
	    
	    // Récupération des contrats "shoppable"
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    // Comptage des contrats par type
	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();
	    // Ajout des comptages au modèle
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
	    model.addAttribute("workshops", formattedWorkshops);
		model.addAttribute("tenancyAlias", tenancyAlias);
		addGraphismAttributes(tenancyAlias, model);

		return "amap/front/shop-workshops";
	}

	/**
	 * Displays details for a specific contract by id.
	 */
	@GetMapping("/contracts/{id}")
	public String showContractDetails(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {

		Long cartId = 1L; // Remplacez par une logique qui récupère le cartId de l'utilisateur
		model.addAttribute("cartId", cartId);

		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		// Récupérer le contrat par ID
		Contract contract = contractService.findById(id);

		// Calcul de la date de première livraison
//		LocalDate today = LocalDate.now();
//		DayOfWeek deliveryDayOfWeek = DayOfWeek.valueOf(contract.getDeliveryDay().name());
//		LocalDate nextDeliveryDate = today.with(TemporalAdjusters.nextOrSame(deliveryDayOfWeek));
		
//	    // Formatter pour les dates
//	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
//	    // Formatage des dates
//	    String formattedEndDate = contract.getEndDate().format(dateFormatter);
//	    String formattedNextDeliveryDate = nextDeliveryDate.format(dateFormatter);
//
//		// Si le jour de livraison correspond à aujourd'hui, passe à la semaine suivante
//		if (today.getDayOfWeek() == deliveryDayOfWeek) {
//			nextDeliveryDate = today.with(TemporalAdjusters.next(deliveryDayOfWeek));
//		}

		// Ajouter l'adresse associée au modèle
		if (contract.getAddress() != null) {
			model.addAttribute("address", contract.getAddress());
		} else if (contract.getTenancy() != null && contract.getTenancy().getAddress() != null) {
			model.addAttribute("address", contract.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}
		
//	    model.addAttribute("formattedEndDate", formattedEndDate); // Ajout de la date formatée
//	    model.addAttribute("formattedNextDeliveryDate", formattedNextDeliveryDate);
		model.addAttribute("contract", contract);
//		model.addAttribute("nextDeliveryDate", nextDeliveryDate);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-contract-detail";
	}

	/**
	 * Displays details for a specific product by id.
	 */
	@GetMapping("/products/{id}")
	public String showProductDetails(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {

		Long cartId = 1L; // Remplacez par une logique qui récupère le cartId de l'utilisateur
		model.addAttribute("cartId", cartId);

		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		// Récupérer le contrat par ID
		Product product = productService.findById(id);

//		// Calcul de la date de première livraison
//		LocalDate today = LocalDate.now();
//		DayOfWeek deliveryDayOfWeek = DayOfWeek.valueOf(product.getDeliveryDay().name());
//		LocalDate nextDeliveryDate = today.with(TemporalAdjusters.nextOrSame(deliveryDayOfWeek));
//
//		// Si le jour de livraison correspond à aujourd'hui, passe à la semaine suivante
//		if (today.getDayOfWeek() == deliveryDayOfWeek) {
//			nextDeliveryDate = today.with(TemporalAdjusters.next(deliveryDayOfWeek));
//		}
//		
//	    // Formatter pour les dates
//	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
//	    // Formatage des dates
//	    String formattedFabricationDate = product.getFabricationDate().format(dateFormatter);
//	    String formattedExpirationDate = product.getExpirationDate().format(dateFormatter);


		// Ajouter l'adresse associée au modèle
		if (product.getAddress() != null) {
			model.addAttribute("address", product.getAddress());
		} else if (product.getTenancy() != null && product.getTenancy().getAddress() != null) {
			model.addAttribute("address", product.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}

//		model.addAttribute("formattedFabricationDate", formattedFabricationDate);
//		model.addAttribute("formattedExpirationDate", formattedExpirationDate);
		model.addAttribute("product", product);
	    // Récupération des contrats "shoppable"
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    // Comptage des contrats par type
	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();
	    // Ajout des comptages au modèle
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
//		model.addAttribute("nextDeliveryDate", nextDeliveryDate);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-product-detail";
	}

	/**
	 * Displays details for a specific contract by id.
	 */
	@GetMapping("/workshops/{id}")
	public String showWorkshopDetails(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {
		
		Long cartId = 1L; // Remplacez par une logique qui récupère le cartId de l'utilisateur
		model.addAttribute("cartId", cartId);
		
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		
		// Récupérer le contrat par ID
		Workshop workshop = workshopService.findById(id);
		
		// Ajouter l'adresse associée au modèle
		if (workshop.getAddress() != null) {
			model.addAttribute("address", workshop.getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}
	    // Formater le champ LocalDateTime
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'à' HH:mm", Locale.FRENCH);
	    String formattedWorkshopDateTime = workshop.getWorkshopDateTime().format(formatter);
	    model.addAttribute("formattedWorkshopDateTime", formattedWorkshopDateTime);
		model.addAttribute("workshop", workshop);
	    // Récupération des contrats "shoppable"
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    // Comptage des contrats par type
	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();
	    // Ajout des comptages au modèle
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-workshop-detail";
	}
	
	public void addGraphismAttributes(String alias, Model model) {
		// get map style depending on tenancy
		model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
		model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
		model.addAttribute("latitude", graphismService.getLatitudeByTenancyAlias(alias));
		model.addAttribute("longitude", graphismService.getLongitudeByTenancyAlias(alias));
		// get tenancy info for header footer
		model.addAttribute("tenancy", graphismService.getTenancyByAlias(alias));
		// get color palette
		model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
		// get font choice
		model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	}
}
