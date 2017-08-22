package br.com.gbraghim.eventmanager.controller;

import br.com.gbraghim.eventmanager.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.Response;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Response getAllUsers() {
        return Response.ok(userService.findAll()).build();
    }

}
