package co.ke.coffee_sacco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List index() {
        return userService.fetchUsers();
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.createUser(user);
    }
}
