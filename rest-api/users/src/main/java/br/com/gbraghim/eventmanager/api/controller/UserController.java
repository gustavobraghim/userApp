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
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) throws ResourceNotFoundException {
        return userService.registraCliente(user.getId(), user.getEmail(), user.getPassword(), user.getNome());
    }

    @RequestMapping(value = "/{userId}/userName", method = RequestMethod.PUT)
    public void editUserName(@PathVariable UUID userId, @RequestBody User user) {
        String nomeAux = user.getNome();
        userService.alteraNome(userId, nomeAux);
    }

    @RequestMapping(value = "/{userId}/userPassword", method = RequestMethod.PUT)
    public void editUserPassword(@PathVariable UUID userId, @RequestBody User user) {
        String passwordAux = user.getPassword();
        userService.alteraPassword(userId, passwordAux);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deletaUser(userId);
    }
}