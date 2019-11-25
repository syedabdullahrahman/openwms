package wms.controller.manage.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wms.repository.user.RoleRepository;
import wms.repository.user.UserRepository;

@Controller
public class UsersController {
	
	@Autowired 
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/manage/users")
	public String viewManageUsersPage(Model model) {
		return "manage/users/panel";
	}
		
}