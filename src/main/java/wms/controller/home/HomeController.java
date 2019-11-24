package wms.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String viewWelcomePage() {
		return "home/home";
	}
	
	@GetMapping("/info")
	public String viewInfoPage() {
		return "home/info";
	}
		
}