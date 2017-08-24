package br.com.gbraghim.eventmanager.service;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void registraCliente(UUID id, String nEmail, String nPassword, String nNome){
        if (userDAO.checkEmail(nEmail)==0){ //estou garantindo que o email do usuario é unico
            User usuario = new User();
            usuario.setId(id);
            usuario.setEmail(nEmail);
            usuario.setNome(nNome);
            usuario.setPassword(nPassword);
            System.out.println("Registrando usuario: " + usuario);
            userDAO.createUser(usuario);
        }
        else {
            new ResourceNotFoundException("Erro: já tem o email no banco. LANÇAR A EXCEPTION");
        }
    }

    public void alteraNome(UUID uuid, String nomeNovo){
        User existingUser = userDAO.getById(uuid);
        existingUser.setNome(nomeNovo);
        userDAO.updateUser(existingUser);
    }

    public void alteraPassword(UUID uuid, String nomePassword){
        User existingUser = userDAO.getById(uuid);
        existingUser.setPassword(nomePassword);
        userDAO.updateUser(existingUser);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }



 @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public User findById(UUID userId) throws ResourceNotFoundException {
        return Optional.ofNullable(userDAO.getById(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
    }
}