package isika.p3.amappli.controllers;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.entities.contract.DeliveryDay;
import isika.p3.amappli.entities.contract.DeliveryRecurrence;
import isika.p3.amappli.service.ContractService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/amap/contracts")
public class ContractController {

	private final ContractService contractService;

	public ContractController(ContractService contractService) {
		this.contractService = contractService;
	}

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

	@GetMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		return "amap/contract-form";
	}
	
	private String formatContractName(String name) {
	    if (name == null || name.isEmpty()) {
	        return name;
	    }
	    name = name.trim().toLowerCase();
	    return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	private String formatContractDescription(String description) {
	    if (description == null || description.isEmpty()) {
	        return description;
	    }

	    description = description.trim().toLowerCase();
	    String[] sentences = description.split("\\.\\s*");

	    StringBuilder formattedDescription = new StringBuilder();
	    for (String sentence : sentences) {
	        if (!sentence.isEmpty()) {
	            formattedDescription.append(sentence.substring(0, 1).toUpperCase())
	                                .append(sentence.substring(1))
	                                .append(". ");
	        }
	    }

	    // Supprimer l'espace supplémentaire à la fin
	    return formattedDescription.toString().trim();
	}


	@PostMapping("/add")
	public String addContract(@Valid @ModelAttribute("contract") Contract contract, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // Ajouter les listes nécessaires à la vue en cas d'erreur
	        model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
	        model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
	        model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
	        model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
	        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        model.addAttribute("currentDate", currentDate);
	        
	        return "amap/contract-form"; // Retourner au formulaire en cas d'erreur
	    }
	    
	    // Formatage des champs
	    contract.setContractName(formatContractName(contract.getContractName()));
	    contract.setContractDescription(formatContractDescription(contract.getContractDescription()));
	    contract.setDateCreation(LocalDate.now());
	    
	    // Enregistrer le contrat
	    contractService.save(contract);
	    
	    return "redirect:/amap/contracts/form";
	}


	@GetMapping("/list")
	public String listContracts(Model model) {
		List<Contract> contracts = contractService.findAll();
		contracts.sort(Comparator.comparing(Contract::getContractName, String.CASE_INSENSITIVE_ORDER));
		model.addAttribute("contracts", contracts);
		return "amap/contract-list";
	}

	@PostMapping("/delete/{id}")
	public String deleteContract(@PathVariable("id") Long id) {
		contractService.deletedById(id);
		return "redirect:/amap/contracts/list";
	}

	@GetMapping("/edit/{id}")
	public String editContractForm(@PathVariable("id") Long id, Model model) {
		Contract contract = contractService.findById(id);
		model.addAttribute("contract", contract);
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		model.addAttribute("deliveryRecurrence", Arrays.asList(DeliveryRecurrence.values()));
		model.addAttribute("deliveryDay", Arrays.asList(DeliveryDay.values()));
		return "amap/contract-edit";
	}

	@PostMapping("/update")
	public String updateContract(@ModelAttribute("contract") Contract updatedContract) {
		Contract existingContract = contractService.findById(updatedContract.getId());

		existingContract.setContractName(formatContractName(updatedContract.getContractName()));
		existingContract.setContractType(updatedContract.getContractType());
	    existingContract.setContractDescription(formatContractDescription(updatedContract.getContractDescription()));
		existingContract.setContractWeight(updatedContract.getContractWeight());
		existingContract.setContractPrice(updatedContract.getContractPrice());
		existingContract.setStartDate(updatedContract.getStartDate());
		existingContract.setEndDate(updatedContract.getEndDate());
		existingContract.setImageUrl(updatedContract.getImageUrl());
		existingContract.setDeliveryRecurrence(updatedContract.getDeliveryRecurrence());
		existingContract.setDeliveryDay(updatedContract.getDeliveryDay());
		existingContract.setQuantity(updatedContract.getQuantity());

		contractService.save(existingContract);
		return "redirect:/amap/contracts/list";
	}

}
