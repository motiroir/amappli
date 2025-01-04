package isika.p3.amappli.controllers.amappli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.UserService;

@Controller
@RequestMapping("/plateform")
public class HomeController {

	private final UserService userService;
	
	
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private GraphismService graphismService;
	
	@GetMapping()
	public String home(Model model) {
//		model.addAttribute("message","Hadoken");
		return "amappli/home";
	}
	
	@GetMapping("/writeusers")
	public String writeusers() {
		userService.generateUsers();
		return "test";
	}

	@GetMapping("contactAmappli")
	public String contact() {
	    return "amappli/front/contactAmappli"; 
	}
	
	@GetMapping("aboutAmappli")
	public String aboutUs() {
	    return "amappli/front/aboutAmappli"; 
	}
	
	@GetMapping("features")
	public String getfeatures() {
	    return "amappli/front/featuresPage"; 
	}

}
