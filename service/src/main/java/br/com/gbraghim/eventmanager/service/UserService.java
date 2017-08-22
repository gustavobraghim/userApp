package br.com.gbraghim.eventmanager.service;
import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void registraCliente(String nEmail, String nPassword, String nNome){
        User usuario = new User();
        usuario.setEmail(nEmail);
        usuario.setNome(nNome);
        usuario.setPassword(nPassword);

        //Persistencia
        userDAO.createUser(usuario);
    }

    public void alteraNome(String email, String nomeNovo){
        User existingUser = userDAO.getByEmail(email);
        existingUser.setNome(nomeNovo);
        //Persistencia
        userDAO.updateUser(existingUser);
    }

    public void alteraPassword(String email, String nomePassword){
        User existingUser = userDAO.getByEmail(email);
        existingUser.setPassword(nomePassword);
        //Persistencia
        userDAO.updateUser(existingUser);
    }


    public List<User> findAll() {
        return userDAO.findAll();
    }
}
