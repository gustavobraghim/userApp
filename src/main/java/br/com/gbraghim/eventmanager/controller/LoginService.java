package br.com.gbraghim.eventmanager.controller;
import br.com.gbraghim.eventmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.gbraghim.eventmanager.model.User;
import br.com.gbraghim.eventmanager.model.UserDAO;


//criando um serviço (aqui ligamos a dependency injection)
@Service
public class LoginService {
    @Autowired //pelo que entendi trabalha em conjunto com o @service realizando a dependency injection
    UserDAO userDAO;

    public boolean validateUser(String email, String password) {
        User user = userDAO.getByEmail(email);
        if (user == null){
            return false;
        }

        String auxPassword = user.getPassword();

        if (password == auxPassword){
            return true;
        }
        else{
            return false;
        }
	}
}