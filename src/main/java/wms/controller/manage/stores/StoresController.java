package wms.controller.manage.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoresController {

	@Autowired
	MessageSource messageSource;


	@GetMapping("/manage/stores")
	public String viewActiveStoresPage(Model model) {
		//model.addAttribute("users", (userRepository.findActiveUsers()));
		model.addAttribute("active", true);
		return "manage/stores/panel";
	}
	@GetMapping("/manage/stores/inactive")
	public String viewInactiveStoresPage(Model model) {
		//model.addAttribute("users", (userRepository.findInactiveUsers()));
		model.addAttribute("inactive", true);
		return "manage/stores/panel";
	}
	@GetMapping("/manage/stores/all")
	public String viewManageStoresPage(Model model) {
		//model.addAttribute("users", (userRepository.findAll()));
		model.addAttribute("all", true);
		return "manage/stores/panel";
	}
	
	

}