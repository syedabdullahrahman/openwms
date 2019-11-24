package wms.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementController {

	@GetMapping("/manage")
	public String viewManagementPage() {
		return "manage/index";
	}
		
}