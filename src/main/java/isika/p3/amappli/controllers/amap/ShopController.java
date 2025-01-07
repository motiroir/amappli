package isika.p3.amappli.controllers.amap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.product.Product;
import isika.p3.amappli.entities.tenancy.PickUpSchedule;
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

		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
	            .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);

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
	            if (product.getExpirationDate() != null) {
	                productData.put("expirationDate", product.getExpirationDate().format(formatter));
	            }
	            return productData;
	        })
	        .toList();
	    
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

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
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
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

	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("'Le' dd MMMM yyyy 'à' HH:mm", Locale.FRENCH);

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
	            if (workshop.getWorkshopDateTime() != null) {
	                workshopData.put("workshopDateTime", workshop.getWorkshopDateTime().format(dateTimeFormatter));
	            }
	            return workshopData;
	        })
	        .toList();
	    
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();

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

		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		Contract contract = contractService.findById(id);

		if (contract.getAddress() != null) {
			model.addAttribute("address", contract.getAddress());
		} else if (contract.getTenancy() != null && contract.getTenancy().getAddress() != null) {
			model.addAttribute("address", contract.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}
		
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'le' dd MMMM yyyy", Locale.FRENCH);
	    if (contract.getEndDate() != null) {
	        String formattedEndDate = contract.getEndDate().format(formatter);
	        model.addAttribute("formattedEndDate", formattedEndDate);
	    } else {
	        model.addAttribute("formattedEndDate", "Non spécifiée");
	    }
	    
	    Optional<PickUpSchedule> pickUpSchedule = Optional.ofNullable(tenancy.getPickUpSchedule());
	    if (pickUpSchedule.isPresent()) {
	        LocalDateTime[] nextPickUp = pickUpSchedule.get().getNextPickUpLocalDateTimes();
	        LocalDateTime nextDeliveryDate = nextPickUp[0];

	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("'le' EEEE dd MMMM yyyy 'entre' HH:mm 'et' HH:mm", Locale.FRENCH);
	        String formattedNextDeliveryDate = nextDeliveryDate.format(formatter1);

	        model.addAttribute("formattedNextDeliveryDate", formattedNextDeliveryDate);
	    } else {
	        model.addAttribute("formattedNextDeliveryDate", "Non disponible");
	    }
		
		model.addAttribute("contract", contract);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-contract-detail";
	}

	/**
	 * Displays details for a specific product by id.
	 */
	@GetMapping("/products/{id}")
	public String showProductDetails(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {

		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		Product product = productService.findById(id);

		if (product.getAddress() != null) {
			model.addAttribute("address", product.getAddress());
		} else if (product.getTenancy() != null && product.getTenancy().getAddress() != null) {
			model.addAttribute("address", product.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null);
		}
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    model.addAttribute("product", product);
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("contracts", contracts);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-product-detail";
	}

	/**
	 * Displays details for a specific contract by id.
	 */
	@GetMapping("/workshops/{id}")
	public String showWorkshopDetails(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {
		
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		
		Workshop workshop = workshopService.findById(id);
				if (workshop.getAddress() != null) {
			model.addAttribute("address", workshop.getAddress());
		} else {
			model.addAttribute("address", null);
		}
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'à' HH:mm", Locale.FRENCH);
	   
	    String formattedWorkshopDateTime = workshop.getWorkshopDateTime().format(formatter);
	    List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);

	    long vegetableCount = contracts.stream()
	            .filter(contract -> "VEGETABLES_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long fruitCount = contracts.stream()
	            .filter(contract -> "FRUITS_CONTRACT".equals(contract.getContractType().name()))
	            .count();

	    long mixedCount = contracts.stream()
	            .filter(contract -> "MIX_CONTRACT".equals(contract.getContractType().name()))
	            .count();
	    
	    model.addAttribute("vegetableCount", vegetableCount);
	    model.addAttribute("fruitCount", fruitCount);
	    model.addAttribute("mixedCount", mixedCount);
	    model.addAttribute("formattedWorkshopDateTime", formattedWorkshopDateTime);
	    model.addAttribute("workshop", workshop);
	    model.addAttribute("contracts", contracts);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/front/shopping-workshop-detail";
	}
	
	public void addGraphismAttributes(String alias, Model model) {
		model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
		model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
		model.addAttribute("latitude", graphismService.getLatitudeByTenancyAlias(alias));
		model.addAttribute("longitude", graphismService.getLongitudeByTenancyAlias(alias));
		model.addAttribute("tenancy", graphismService.getTenancyByAlias(alias));
		model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
		model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	}
}
