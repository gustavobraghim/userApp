package br.com.gbraghim.eventmanager.api.controller;

import br.com.gbraghim.eventmanager.model.domain.User;
import br.com.gbraghim.eventmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(User user) {
        System.out.println(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public void editUser(@PathVariable String userId, User user) {
        System.out.println(user);
    }

}
