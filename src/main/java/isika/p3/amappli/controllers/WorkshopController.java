package isika.p3.amappli.controllers;

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

import isika.p3.amappli.dto.WorkshopDTO;
import isika.p3.amappli.entities.workshop.Workshop;
import isika.p3.amappli.service.WorkshopService;

@Controller
@RequestMapping("/amap/workshops")
public class WorkshopController {

	private final WorkshopService workshopService;

	public WorkshopController(WorkshopService workshopService) {
		this.workshopService = workshopService;
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
	public String showForm(Model model) {
		model.addAttribute("workshop", new Workshop());
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		model.addAttribute("currentDate", currentDate);
		return "amap/workshop-form";
	}

	/**
	 * Saves a new product to the database.
	 */
	@PostMapping("/add")
	public String addWorkshop(@ModelAttribute("workshopDTO") WorkshopDTO workshopDTO) {
		workshopService.save(workshopDTO);
		return "redirect:/amap/workshops/list";
	}

	/**
	 * Displays a list of all workshops.
	 */
	@GetMapping("/list")
	public String listWorkshops(Model model) {
		List<Workshop> workshops = workshopService.findAll();
		model.addAttribute("workshops", workshops);
		return "amap/workshop-list";
	}

	/**
	 * Deletes a contract by its ID.
	 */
	@PostMapping("/delete/{id}")
	public String deleteWorkshop(@PathVariable("id") Long id) {
		workshopService.deleteById(id);
		return "redirect:/amap/workshops/list";
	}

	/**
	 * Displays the details of a specific product.
	 */
	@GetMapping("/detail/{id}")
	public String viewWorkshopDetail(@PathVariable("id") Long id, Model model) {
		Workshop workshop = workshopService.findById(id);
		model.addAttribute("workshop", workshop);
		return "amap/workshop-detail";
	}

}
