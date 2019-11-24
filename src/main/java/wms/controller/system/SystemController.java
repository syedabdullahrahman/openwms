package wms.controller.system;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wms.model.user.Role;
import wms.model.user.User;
import wms.repository.user.RoleRepository;
import wms.repository.user.UserRepository;
import wms.service.system.DatabaseManagementService;

@Controller
public class SystemController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
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
		
		@GetMapping("/mng")
		public String viewManagePage(Model model) {
			Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

			model.addAttribute("dateDec", day + "." + month + "." + year);
			model.addAttribute("dateBin",
					Integer.toBinaryString(day) + "." + Integer.toBinaryString(month) + "." + Integer.toBinaryString(year));
			model.addAttribute("dateHex",
					Integer.toHexString(day) + "." + Integer.toHexString(month) + "." + Integer.toHexString(year));
			model.addAttribute("dayOfYearDec", dayOfYear);
			model.addAttribute("dayOfYearBin", Integer.toBinaryString(dayOfYear));
			model.addAttribute("dayOfYearHex", Integer.toHexString(dayOfYear));

			//model.addAttribute("error", "$@*&#@*#(");

			model.addAttribute("msg","hello there");
			
			System.out.println(userRepository.findByUsernameIgnoreCase("Manager"));
			User user = userRepository.findByUsernameIgnoreCase("Manager");
			
			Role std = roleRepository.findByNameIgnoreCase("role_standard");
			System.out.println(std);
			user.addRole(std);
			userRepository.save(user);
			System.out.println(userRepository.findByUsernameIgnoreCase("Manager"));
			user.getRoles().remove(std);
			userRepository.save(user);
			System.out.println(userRepository.findByUsernameIgnoreCase("Manager"));
			
			
			
			return "home/home";
		}
		
}