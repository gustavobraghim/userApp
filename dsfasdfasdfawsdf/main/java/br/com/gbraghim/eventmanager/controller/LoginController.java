package br.com.gbraghim.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    public static final String INDEX_PAGE = "index";

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showLoginPage() {
        return INDEX_PAGE; //exibe a pagina index
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String handleLoginRequest(String email, String password, ModelMap model) {
        if (!service.validateUser(email, password)) {
            model.put("errorMessage", "Credenciais invalidas");
            return INDEX_PAGE;
        } else {
            model.put("email", email);
            model.put("password", password);
            return "welcome";
        }
    }
}