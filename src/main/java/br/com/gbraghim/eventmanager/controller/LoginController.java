package br.com.gbraghim.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.gbraghim.eventmanager.controller.LoginService;

@Controller
public class LoginController {
    // Aqui estou setando o login service (aqui ocorre dependency injection, que vem do Login Service)
    @Autowired //pelo que entendi trabalha em conjunto com o @service realizando a dependency injection
    LoginService service;

    //metodo com get
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showLoginPage(){
            return "index"; //exibe a pagina index
    }

    //metodo com post
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String handleLoginRequest(@RequestParam String email, @RequestParam String password, @RequestParam String name,  ModelMap model){
       if (!service.validateUser(email, password)){
          model.put("errorMessage", "Credenciais invalidas");
           return "index"; // se a senha tiver errada volta para a tela de login
       }

       //segue o fluxo caso tudo esteja ok e mostra
        model.put("name", name);
        model.put("email", email);
        model.put("password", password);
        return "welcome";
    }
}