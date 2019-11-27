package wms.controller.manage.users;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;
import wms.model.user.Role;
import wms.model.user.User;
import wms.repository.user.RoleRepository;
import wms.repository.user.UserRepository;

@Controller
public class UsersController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	MessageSource messageSource;

	@GetMapping("/manage/users")
	public String viewManageUsersPage(Model model) {
		model.addAttribute("users", (userRepository.findAllActiveUsers()));
		return "manage/users/panel";
	}

	@GetMapping("/manage/users/edit/{id}")
	public String viewEditUserPage(@PathVariable("id") int id, Model model) throws NotFoundException {

		User user = getUser(id);
		model.addAttribute("allRoles", getAllRoles());
		model.addAttribute("userRoles", user.getRoles());
		model.addAttribute("formUserEdit", new FormUserEdit(user));
		
		return "manage/users/edit";
	}
	
    @PostMapping(value = "/manage/users/edit")
	@Transactional
	public String close(@Valid FormUserEdit formUserEdit, BindingResult bindingResult,
			RedirectAttributes redirectAttrs, Locale locale, Model model) throws NotFoundException {

    	User user = getUser(formUserEdit.getId());
    	
		if(bindingResult.hasErrors()){
			model.addAttribute("allRoles", getAllRoles());
			model.addAttribute("userRoles", user.getRoles());
			return "manage/users/edit";
		}
		
		String oldName = user.getUsername();
		String newName = formUserEdit.getUsername();
		
		if(!oldName.contentEquals(newName)) {
			User userCheck = userRepository.findByUsernameIgnoreCase(newName);
			if(userCheck!=null) {
				bindingResult.rejectValue("username", "users.nametaken", "ERROR");
			}
		}
		
		if(bindingResult.hasErrors()){
			model.addAttribute("allRoles", getAllRoles());
			model.addAttribute("userRoles", user.getRoles());
			return "manage/users/edit";
		}
		
		user.setUsername(formUserEdit.getUsername());
		user.setName(formUserEdit.getName());
		user.setActive(formUserEdit.isActive());
		
		
		userRepository.save(user);
		

		redirectAttrs.addFlashAttribute("msg", messageSource.getMessage("action.saved", null, locale));
		return "redirect:/manage/users/edit/" + user.getId();
		
	}
    
	@GetMapping("/manage/users/roles/{action}/{userId}/{roleId}")
	public String addPage(
			@PathVariable("action") String action, 
			@PathVariable("userId") int userId, 
			@PathVariable("roleId") int roleId, Model model,
			RedirectAttributes redirectAttrs,
			Locale locale
			) throws Exception {

		if(!action.equals("add") && !action.equals("remove")) {
			throw new Exception("Unknown operation: " + action);
		}
		
		User user = getUser(userId);

		Optional<Role> role = roleRepository.findById(roleId);
		if (!role.isPresent()) {
			throw new NotFoundException("Unknown role: #" + roleId);
		}
	
		switch(action) {
			case "add":
				user.getRoles().add(role.get());
				userRepository.save(user);
				break;
			case "remove":
				user.getRoles().remove(role.get());
				userRepository.save(user);
				break;
			default:
				break;
			}
		
		redirectAttrs.addFlashAttribute("msg", messageSource.getMessage("action.saved", null, locale));
		return "redirect:/manage/users/edit/"+ user.getId();
	}
	
	private User getUser(int id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new NotFoundException("Unknown user: #" + id);
		}
		return user.get();
	}
	
	private List<Role> getAllRoles(){
		return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
}