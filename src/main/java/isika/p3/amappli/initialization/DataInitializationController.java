package isika.p3.amappli.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataInitializationController {
	
	@Autowired
	private DataInitializationService initService;

	@GetMapping("/init")
	public String initializeData() {
		initService.dataInit();
		return "";
	}
	
}
