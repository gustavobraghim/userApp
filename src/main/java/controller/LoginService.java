package controller;

import org.springframework.stereotype.Service;

//criando um serviço (aqui ligamos a dependency injection)
@Service
public class LoginService {
    public boolean validateUser(String email, String password) {

		//return user.equalsIgnoreCase("gustavo") && password.equals("daitan");
		return false;
	}

}