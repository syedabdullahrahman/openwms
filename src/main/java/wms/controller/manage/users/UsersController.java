package wms.controller.manage.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javassist.NotFoundException;
import wms.model.user.User;
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
		model.addAttribute("users", (userRepository.findAllActiveUsers()));
		return "manage/users/panel";
	}

	@GetMapping("/manage/users/edit/{id}")
	public String viewEditUserPage(@PathVariable("id") int id, Model model) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new NotFoundException("exception test");
		}
		model.addAttribute("user", user.get());
		return "manage/users/edit";
	}
	
	
}