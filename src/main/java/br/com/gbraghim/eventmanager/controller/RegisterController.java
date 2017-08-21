package br.com.gbraghim.eventmanager.controller;

import br.com.gbraghim.eventmanager.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.gbraghim.eventmanager.controller.LoginService;
import br.com.gbraghim.eventmanager.model.User;

import java.sql.*;

@Controller
public class RegisterController {
    public static final String SIGNUP_PAGE = "signup";

    @Autowired
    private UserDAO userDAO;

    User user = new User();
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String tryAgain() {
        return SIGNUP_PAGE; //exibe a pagina signup
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String register(@RequestParam String name, @RequestParam String password, @RequestParam String email, ModelMap model) throws SQLException {
        model.put("name", name);
        model.put("password", password);
        model.put("email", email);

        //banco de dados
        //user.registraCliente(name, password, email);






        return "registerOK";
    }
}