package isika.p3.amappli.controllers.amap;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.dto.amap.ContractDTO;
import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.ContractService;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;

@Controller
@RequestMapping("/amap/{tenancyAlias}/admin/contracts")
public class ContractController {

	private final ContractService contractService;
	private final TenancyRepository tenancyRepository;
	private final AmapAdminUserService AmapAdminUserService;
    private final GraphismService graphismService;

	public ContractController(GraphismService graphismService, AmapAdminUserService AmapAdminUserService, ContractService contractService,
			TenancyRepository tenancyrepository) {
		this.contractService = contractService;
		this.AmapAdminUserService = AmapAdminUserService;
		this.tenancyRepository = tenancyrepository;
		this.graphismService = graphismService;
	}

	/**
	 * Initializes custom data binding for LocalDate.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text == null || text.isEmpty()) {
					setValue(null);
				} else {
					setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
			}
		});
	}

	/**
	 * Displays the form for adding a new contract.
	 */
	@GetMapping("/form")
	public String showForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		
		model.addAttribute("contract", new Contract());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("users", users);
	    // Récupérer l'adresse de la tenancy
	    Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
	            .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
	    Address address = tenancy.getAddress();
	    model.addAttribute("address", address);
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("pickupSchedule", tenancy.getPickUpSchedule());
		addGraphismAttributes(tenancyAlias, model);

		return "amap/back/contracts/contract-form";
	}

	/**
	 * Saves a new contract to the database.
	 */
	@PostMapping("/add")
	public String addContract(@ModelAttribute("contractDTO") ContractDTO newContractDTO,
			@PathVariable("tenancyAlias") String tenancyAlias) {
		contractService.save(newContractDTO, tenancyAlias);
		return "redirect:/amap/" + tenancyAlias + "/admin/contracts/list";

	}

	/**
	 * Displays a list of all contracts.
	 */
	@GetMapping("/list")
	public String listContracts(Model model, @PathVariable String tenancyAlias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		List<Contract> contracts = contractService.findAll(tenancyAlias);
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("users", users);
		model.addAttribute("contracts", contracts);
		model.addAttribute("tenancyAlias", tenancyAlias);
		addGraphismAttributes(tenancyAlias, model);
		return "amap/back/contracts/contract-list";
	}

	/**
	 * Deletes a contract by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteContract(@PathVariable("id") Long id, @PathVariable("tenancyAlias") String tenancyAlias) {
		contractService.deleteById(id);
		return "redirect:/amap/" + tenancyAlias + "/admin/contracts/list";
	}

	/**
	 * Displays the edit form for a specific contract.
	 */
	@GetMapping("/edit/{id}")
	public String editContractForm(@PathVariable("tenancyAlias") String tenancyAlias, @PathVariable("id") Long id,
			Model model) {
		Contract contract = contractService.findById(id);

		if (contract == null) {
			return "redirect:/" + tenancyAlias + "/admin/contracts/list"; // Redirige si le contrat n'existe pas
		}
	    Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
	            .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
	    Address address = tenancy.getAddress();
	    model.addAttribute("address", address);
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("users", users);
		model.addAttribute("contract", contract);
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		


		return "amap/back/contracts/contract-edit"; // Nom de la vue pour le formulaire d'édition
	}

	/**
	 * Displays the details of a specific contract.
	 */
	@GetMapping("/detail/{id}")
	public String viewContractDetail(@PathVariable("id") Long id, Model model,
			@PathVariable("tenancyAlias") String tenancyAlias) {
		Contract contract = contractService.findById(id);
		if (contract == null) {
			throw new IllegalArgumentException("Contrat introuvable pour l'ID : " + id);
		}
	    Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
	            .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
	    Address address = tenancy.getAddress();
	    model.addAttribute("address", address);
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("users", users);
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		String formattedDate = contract.getDateCreation().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		model.addAttribute("formattedDate", formattedDate);
		model.addAttribute("contract", contract);
		model.addAttribute("tenancyAlias", tenancyAlias);
		addGraphismAttributes(tenancyAlias, model);
		model.addAttribute("pickupSchedule", tenancy.getPickUpSchedule());
		return "amap/back/contracts/contract-detail";
	}

	@PostMapping("/update")
	public String updateContract(
		    @ModelAttribute("contract") ContractDTO updatedContractDTO,
		    @RequestParam(value = "image", required = false) MultipartFile image,
		    @PathVariable("tenancyAlias") String tenancyAlias) {

		contractService.updateContract(updatedContractDTO, image, tenancyAlias);

		return "redirect:/amap/" + tenancyAlias + "/admin/contracts/list";
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