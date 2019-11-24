package wms.controller.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wms.service.system.DatabaseManagementService;

@Controller
public class SystemController {
	
	@Autowired
	private DatabaseManagementService dbManager;

	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
	@GetMapping("/noaccess")
	public String viewNoAccessPage() {
		return "noaccess";
	}
	
	@GetMapping("/dbinit")
	public String initDatabase(Model model) {
		model.addAttribute("msg",dbManager.initializeDatabase());
		return "home/home";
	}

	
	// -------------------------------------- tests to clear ------------------------------------------
		@GetMapping("/err")
		public String viewFakeError() throws Exception {
			throw new Exception("Fake Exception");
		}
		
		
}		