package isika.p3.amappli.controllers.amap;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import isika.p3.amappli.dto.amap.WorkshopDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.entities.workshop.Workshop;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.WorkshopService;

@Controller
@RequestMapping("/{tenancyAlias}/backoffice/workshops")
public class WorkshopController {

	private final WorkshopService workshopService;
	private final TenancyRepository tenancyRepository;
	private final AmapAdminUserService AmapAdminUserService;

	public WorkshopController(WorkshopService workshopService, TenancyRepository tenancyRepository, AmapAdminUserService AmapAdminUserService) {
		this.workshopService = workshopService;
		this.tenancyRepository = tenancyRepository;
		this.AmapAdminUserService = AmapAdminUserService;
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
	 * Displays the form for adding a new workshop.
	 */
	@GetMapping("/form")
	public String showForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		Address address = tenancy.getAddress();
		
		model.addAttribute("workshop", new Workshop());
		model.addAttribute("tenancyAlias", tenancyAlias);
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("users", users);
	    model.addAttribute("address", address);
		return "amap/back/workshops/workshop-form";
	}

	/**
	 * Saves a new workshop to the database.
	 */
	@PostMapping("/add")
	public String addWorkshop(@ModelAttribute("workshopDTO") WorkshopDTO workshopDTO,  @PathVariable("tenancyAlias") String tenancyAlias) {
		workshopService.save(workshopDTO, tenancyAlias);
	    if (workshopDTO.getWorkshopName() == null || workshopDTO.getWorkshopName().isEmpty()) {
	        throw new IllegalArgumentException("Le champ 'Nom du produit' est obligatoire.");
	    }
		return "redirect:/" + tenancyAlias + "/backoffice/workshops/list";
	}

	/**
	 * Displays a list of all workshops.
	 */
	@GetMapping("/list")
	public String listWorkshops(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		List<Workshop> workshops = workshopService.findAll();
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
				.orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
		model.addAttribute("workshops", workshops);
		model.addAttribute("users", users);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/workshops/workshop-list";
	}

	/**
	 * Deletes a workshop by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteWorkshop(@PathVariable("id") Long id, @PathVariable("tenancyAlias") String tenancyAlias) {
		workshopService.deleteById(id);
		return "redirect:/" + tenancyAlias + "/backoffice/workshops/list";
	}

	/**
	 * Displays the edit form for a specific workshop.
	 */
	@GetMapping("/edit/{id}")
	public String editWorkshopForm(@PathVariable("id") Long id, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
	    Workshop workshop = workshopService.findById(id);

	    if (workshop == null) {
	        return "redirect:/amap/workshops/list"; // Redirige si le contrat n'existe pas
	    }
	    
	    // Formater la date et l'heure pour le champ datetime-local
	    if (workshop.getWorkshopDateTime() != null) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	        String formattedDateTime = workshop.getWorkshopDateTime().format(formatter);
	        model.addAttribute("workshopDateTime", formattedDateTime);
	    }
	    
	    // Récupérer l'utilisateur associé
	    User user = workshop.getUser();
	    Address address = null;
	    if (user != null) {
	        address = user.getAddress(); // Récupérer l'adresse associée à l'utilisateur
	    }
		List<User> users = AmapAdminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("users", users);
	    model.addAttribute("address", address);
	    model.addAttribute("workshop", workshop);
	    model.addAttribute("tenancyAlias", tenancyAlias);

	    return "amap/back/workshops/workshop-edit"; // Nom de la vue pour le formulaire d'édition
	}
	
	/**
	 * Displays the details of a specific workshop.
	 */
	@GetMapping("/detail/{id}")
	public String viewWorkshopDetail(@PathVariable("id") Long id, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		Workshop workshop = workshopService.findById(id);
		if (workshop == null) {
			throw new IllegalArgumentException("Contrat introuvable pour l'ID : " + id);
		}
		String formattedDate = workshop.getDateCreation().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		model.addAttribute("formattedDate", formattedDate);
		model.addAttribute("workshop", workshop);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/workshops/workshop-detail";
	}
	
	@PostMapping("/update")
	public String updateWorkshop(
	    @ModelAttribute("workshop") WorkshopDTO updatedWorkshopDTO,
	    @RequestParam(value = "image", required = false) MultipartFile image, @PathVariable("tenancyAlias") String tenancyAlias) {
	    
		workshopService.updateWorkshop(updatedWorkshopDTO, image, tenancyAlias);

		return "redirect:/" + tenancyAlias + "/backoffice/workshops/list";
	}

}
