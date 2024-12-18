package isika.p3.amappli.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.contract.ContractWeight;
import isika.p3.amappli.service.ContractService;

@Controller
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

	@GetMapping("/contracts/form")
	public String showForm(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("contractTypes", Arrays.asList(ContractType.values()));
		model.addAttribute("contractWeights", Arrays.asList(ContractWeight.values()));
		return "contract-form";
	}

	@PostMapping("/contracts/add")
	public String addContract(@ModelAttribute("contract") Contract contract) {
		contract.setDateCreation(LocalDate.now());
		contractService.save(contract);
		return "redirect:/contracts/form";
	}

	@GetMapping("/contracts/list")
	public String listContracts(Model model) {
		List<Contract> contracts = contractService.findAll();
		model.addAttribute("contracts", contracts);
		return "contract-list";
	}

}
