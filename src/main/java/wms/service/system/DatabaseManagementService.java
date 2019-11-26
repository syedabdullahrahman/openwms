package wms.service.system;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import wms.model.user.Role;
import wms.model.user.User;
import wms.repository.user.RoleRepository;
import wms.repository.user.UserRepository;

@Service
public class DatabaseManagementService {

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public String initializeDatabase() {
		String result = "";
		
        // Delete roles
        this.roleRepository.deleteAll();
        // Create roles
        Role standardR = new Role("STANDARD");
        Role managerR = new Role("MANAGER");
        Role adminR = new Role("ADMIN");
        List<Role> roles = Arrays.asList(standardR,managerR,adminR);
        // Save to db
        this.roleRepository.saveAll(roles);
        result += "[!Roles:] " + standardR + "; " + managerR + "; " + adminR;
		
        // Delete users
        this.userRepository.deleteAll();
        // Create users
        User userU = new User("User", "Peter Parker", passwordEncoder.encode("user"));
        userU.addRole(standardR);
        User managerU = new User("Manager", "Clark Kent", passwordEncoder.encode("manager"));
        managerU.addRole(managerR);
        User adminU = new User("Admin", "Bruce Wayne", passwordEncoder.encode("admin"));
        adminU.addRole(adminR);
        List<User> users = Arrays.asList(userU,adminU,managerU);
        // Save to db
        this.userRepository.saveAll(users);
        result += "[!Users:] " + userU + "; " + managerU + "; " + adminU;

        return result;
    }
}
