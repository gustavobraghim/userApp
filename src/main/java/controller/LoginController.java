package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import controller.LoginService;

@Controller
public class LoginController {
    // Aqui estou setando o login service (aqui ocorre dependency injection, que vem do Login Service)
    @Autowired //pelo que entendi trabalha em conjunto com o @service realizando a dependency injection
    LoginService service;

    //metodo com get
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(){
            return "login"; //exibe a pagina index
    }

    //metodo com post
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLoginRequest(@RequestParam String name, @RequestParam String password,  ModelMap model){
       if (!service.validateUser(name, password)){
          model.put("errorMessage", "Credenciais invalidas");
           return "login"; // se a senha tiver errada volta para a tela de login
       }

       //segue o fluxo caso tudo esteja ok e mostra
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }
}