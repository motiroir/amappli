package isika.p3.amappli.controllers.amap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

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
import isika.p3.amappli.service.amap.ProductService;
import isika.p3.amappli.service.amap.WorkshopService;

@Controller
@RequestMapping("/{tenancyAlias}/shop")
public class ShopController {

	private final ContractService contractService;
	private final TenancyRepository tenancyRepository;
	private final ProductService productService;
	private final WorkshopService workshopService;

	public ShopController(ContractService contractService, TenancyRepository tenancyRepository,
			ProductService productService, WorkshopService workshopService) {
		this.contractService = contractService;
		this.tenancyRepository = tenancyRepository;
		this.productService = productService;
		this.workshopService = workshopService;
	}

	/**
	 * Displays all shoppable contracts in the shop view.
	 */
	@GetMapping("/contracts")
	public String showAllShoppableContracts(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));

		Long cartId = 1L;
		model.addAttribute("cartId", cartId);

		List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);
		model.addAttribute("contracts", contracts);
		model.addAttribute("tenancyAlias", tenancyAlias);

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

		List<Product> products = productService.findShoppableProductsByTenancy(tenancy);
		model.addAttribute("products", products);
		model.addAttribute("tenancyAlias", tenancyAlias);

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

		List<Workshop> workshops = workshopService.findShoppableWorkshopsByTenancy(tenancy);
		model.addAttribute("workshops", workshops);
		model.addAttribute("tenancyAlias", tenancyAlias);

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
		LocalDate today = LocalDate.now();
		DayOfWeek deliveryDayOfWeek = DayOfWeek.valueOf(contract.getDeliveryDay().name());
		LocalDate nextDeliveryDate = today.with(TemporalAdjusters.nextOrSame(deliveryDayOfWeek));

		// Si le jour de livraison correspond à aujourd'hui, passe à la semaine suivante
		if (today.getDayOfWeek() == deliveryDayOfWeek) {
			nextDeliveryDate = today.with(TemporalAdjusters.next(deliveryDayOfWeek));
		}

		// Ajouter l'adresse associée au modèle
		if (contract.getAddress() != null) {
			model.addAttribute("address", contract.getAddress());
		} else if (contract.getTenancy() != null && contract.getTenancy().getAddress() != null) {
			model.addAttribute("address", contract.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}

		model.addAttribute("contract", contract);
		model.addAttribute("nextDeliveryDate", nextDeliveryDate);
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

		// Calcul de la date de première livraison
		LocalDate today = LocalDate.now();
		DayOfWeek deliveryDayOfWeek = DayOfWeek.valueOf(product.getDeliveryDay().name());
		LocalDate nextDeliveryDate = today.with(TemporalAdjusters.nextOrSame(deliveryDayOfWeek));

		// Si le jour de livraison correspond à aujourd'hui, passe à la semaine suivante
		if (today.getDayOfWeek() == deliveryDayOfWeek) {
			nextDeliveryDate = today.with(TemporalAdjusters.next(deliveryDayOfWeek));
		}

		// Ajouter l'adresse associée au modèle
		if (product.getAddress() != null) {
			model.addAttribute("address", product.getAddress());
		} else if (product.getTenancy() != null && product.getTenancy().getAddress() != null) {
			model.addAttribute("address", product.getTenancy().getAddress());
		} else {
			model.addAttribute("address", null); // Pas d'adresse trouvée
		}

		model.addAttribute("product", product);
		model.addAttribute("nextDeliveryDate", nextDeliveryDate);
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
		
		model.addAttribute("workshop", workshop);
		return "amap/front/shopping-workshop-detail";
	}
}
