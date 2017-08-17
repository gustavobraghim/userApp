package br.com.gbraghim.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.gbraghim.eventmanager.controller.LoginService;
import br.com.gbraghim.eventmanager.model.User;

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

    public String handleLoginRequest(User user, ModelMap model){
        String passwordAux = user.getPassword();
        String emailAux = user.getEmail();

        if (!service.validateUser(emailAux,passwordAux)){
          model.put("errorMessage", "Credenciais invalidas");
           return "index"; // se a senha tiver errada volta para a tela de login
        }

        //segue o fluxo caso tudo esteja ok e mostra
        model.put("email", emailAux);
        model.put("password", passwordAux);
        return "welcome";
    }
}