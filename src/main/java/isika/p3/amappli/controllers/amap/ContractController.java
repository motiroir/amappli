package isika.p3.amappli.controllers.amap;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
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
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.AddressService;
import isika.p3.amappli.service.amap.ContractService;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;

@Controller
@RequestMapping("/{tenancyAlias}/backoffice/contracts")
public class ContractController {

	private final ContractService contractService;
	private final UserService userService;
	private final TenancyService tenancyService;
	private final TenancyRepository tenancyRepository;

	public ContractController(ContractService contractService, TenancyService tenancyService, UserService userService, TenancyRepository tenancyrepository) {
		this.contractService = contractService;
		this.userService = userService;
		this.tenancyService = tenancyService;
		this.tenancyRepository = tenancyrepository;
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
		System.out.println("Tenancy alias: " + tenancyAlias);
		model.addAttribute("contract", new Contract());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
	    model.addAttribute("users", userService.findAll()); // Chargez tous les utilisateurs
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		return "amap/back/contracts/contract-form";
	}

	/**
	 * Format the contractName from the form
	 */
	private String formatContractName(String name) {
		if (name == null || name.isEmpty()) {
			return name;
		}
		name = name.trim().toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * Format the contractDescription from the form
	 */
	private String formatContractDescription(String description) {
		if (description == null || description.isEmpty()) {
			return description;
		}

		description = description.trim().toLowerCase();
		String[] sentences = description.split("\\.\\s*");

		StringBuilder formattedDescription = new StringBuilder();
		for (String sentence : sentences) {
			if (!sentence.isEmpty()) {
				formattedDescription.append(sentence.substring(0, 1).toUpperCase()).append(sentence.substring(1))
						.append(". ");
			}
		}

		// Supprimer l'espace supplémentaire à la fin
		return formattedDescription.toString().trim();
	}

	/**
	 * Saves a new contract to the database.
	 */
	@PostMapping("/add")
	public String addContract(@ModelAttribute("contractDTO") ContractDTO newContractDTO, @PathVariable("tenancyAlias") String tenancyAlias) {
		contractService.save(newContractDTO, tenancyAlias);
		return "redirect:/" + tenancyAlias + "/backoffice/contracts/list";

	}

	/**
	 * Displays a list of all contracts.
	 */
	@GetMapping("/list")
	public String listContracts(Model model, @PathVariable String tenancyAlias) {
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias) .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		List<Contract> contracts = contractService.findAll(tenancyAlias);
		model.addAttribute("contracts", contracts);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/contracts/contract-list";
	}

	/**
	 * Deletes a contract by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteContract(@PathVariable("id") Long id) {
		contractService.deleteById(id);
		return "redirect:/amap/contracts/list";
	}

	/**
	 * Displays the edit form for a specific contract.
	 */
	@GetMapping("/edit/{id}")
	public String editContractForm(@PathVariable("id") Long id, Model model) {
	    Contract contract = contractService.findById(id);

	    if (contract == null) {
	        return "redirect:/amap/contracts/list"; // Redirige si le contrat n'existe pas
	    }

	    model.addAttribute("contract", contract);
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
	public String viewContractDetail(@PathVariable("id") Long id, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Contract contract = contractService.findById(id);
		model.addAttribute("contract", contract);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/contracts/contract-detail";
	}

	@PostMapping("/update")
	public String updateContract(
	    @ModelAttribute("contract") ContractDTO updatedContractDTO,
	    @RequestParam(value = "image", required = false) MultipartFile image) {
	    
	    contractService.updateContract(updatedContractDTO, image);

	    return "redirect:/amap/contracts/list";
	}





}