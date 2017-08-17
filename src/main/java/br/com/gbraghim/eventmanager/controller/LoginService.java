package br.com.gbraghim.eventmanager.controller;
import br.com.gbraghim.eventmanager.model.User;
import org.springframework.stereotype.Service;
import br.com.gbraghim.eventmanager.model.User;

//criando um serviço (aqui ligamos a dependency injection)
@Service
public class LoginService {
    public boolean validateUser(String email, String password) {

        return equals(email) && equals(password);
        //como buscar os dados no banco?
        //return email.equalsIgnoreCase("gustavo") && password.equals("daitan");
	}
}