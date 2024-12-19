package isika.p3.amappli.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.service.UserService;

@Controller
@RequestMapping("/test/")
public class HomeController {

	private final UserService userService;
	
	
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("test")
	public String home(Model model) {
//		model.addAttribute("message","Hadoken");
		return "amappli/home";
	}
	
	@GetMapping("/writeusers")
	public String writeusers() {
		userService.generateUsers();
		return "test";
	}
}
