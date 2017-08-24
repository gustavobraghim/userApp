package br.com.gbraghim.eventmanager.api.controller;

import br.com.gbraghim.eventmanager.model.domain.User;
import br.com.gbraghim.eventmanager.service.UserService;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable UUID userId) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        user.setPassword(null);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody  User user) {
        userService.registraCliente(user.getId(), user.getEmail(), user.getNome(), user.getPassword());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public void editUser(@PathVariable UUID userId, User user) {
        System.out.println(user);
    }

}
