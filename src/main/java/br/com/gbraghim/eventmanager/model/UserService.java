package br.com.gbraghim.eventmanager.model;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserDAO userDAO;

    private void registraCliente(String nEmail, String nPassword, String nNome){
        User usuario = new User();
        usuario.setEmail(nEmail);
        usuario.setNome(nNome);
        usuario.setPassword(nPassword);

        //Persistencia
        userDAO.createUser(usuario);
    }

    private void alteraNome(String email, String nomeNovo){
        User existingUser = userDAO.getByEmail(email);
        existingUser.setNome(nomeNovo);
        //Persistencia
        userDAO.UpdateUser(existingUser);
    }

    private void alteraPassword(String email, String nomePassword){
        User existingUser = userDAO.getByEmail(email);
        existingUser.setPassword(nomePassword);
        //Persistencia
        userDAO.UpdateUser(existingUser);
    }


}
