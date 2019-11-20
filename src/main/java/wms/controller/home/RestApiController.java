package wms.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wms.repository.user.UserRepository;
import wms.model.user.User;

import java.util.List;

@RestController
@RequestMapping("api/public")
public class RestApiController {
	
	@Autowired
    private UserRepository userRepository;

    public RestApiController(UserRepository userRepository){
      //  this.userRepository = userRepository;
    }

    @GetMapping("test1")
    public String test1(){
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "API Test 2";
    }

    @GetMapping("users")
    public List<User> users(){
        return this.userRepository.findAll();
    }

}
