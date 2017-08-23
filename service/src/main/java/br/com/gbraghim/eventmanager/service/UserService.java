package br.com.gbraghim.eventmanager.service;
import br.com.gbraghim.eventmanager.exception.ResouceNotFoundException;
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
        User usuario = new User();
        usuario.setId(id);
        usuario.setEmail(nEmail);
        usuario.setNome(nNome);
        usuario.setPassword(nPassword);

        //String emailAuxiliar = userDAO.getByEmail({$userId});

            if(emailAuxiliar == usuario.getEmail()){
                System.out.println("Erro: já tem o email no banco. LANÇAR A EXCEPTION");
            }
            else {
                System.out.println("Registrando usuario: " + usuario);
                userDAO.createUser(usuario);
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
    public User findById(UUID userId) {
        return Optional.ofNullable(userDAO.getById(userId))
                .orElseThrow(() -> new ResouceNotFoundException("User not found: " + userId));
    }
}