package br.com.gbraghim.eventmanager.controller;
import org.springframework.stereotype.Service;

//criando um serviço (aqui ligamos a dependency injection)
@Service
public class LoginService {
    public boolean validateUser(String email, String password) {

		//como buscar os dados no banco?
        return email.equalsIgnoreCase("gustavo") && password.equals("daitan");
	}

}