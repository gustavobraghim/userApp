package br.com.gbraghim.eventmanager.service;

import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void registraCliente(UUID id, String nEmail, String nPassword, String nNome) throws ResourceNotFoundException {
        if (userDAO.checkEmail(nEmail)==0){ //estou garantindo que o email do usuario é unico
            String passwordAux = nPassword;

            User usuario = new User();
            usuario.setId(id);
            usuario.setEmail(nEmail);
            usuario.setNome(nNome);

            try {
                MessageDigest messageDigest1 = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = messageDigest1.digest(passwordAux.getBytes("UTF-8"));

                StringBuilder stringBuilder = new StringBuilder();

                for (byte b:messageDigest){
                    stringBuilder.append(String.format("%02X", 0xFF & b));
                }
                String senhaHex = stringBuilder.toString();
                usuario.setPassword(senhaHex);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println("Registrando usuario: " + usuario);
            userDAO.createUser(usuario);
        }
        else {
            throw new ResourceNotFoundException("Erro: já tem o email no banco. LANÇAR A EXCEPTION");
        }
    }

    public void alteraNome(UUID uuid, String nomeNovo){
        User existingUser = userDAO.getById(uuid);
        existingUser.setNome(nomeNovo);
        userDAO.updateUser(existingUser);
    }

    public void alteraPassword(UUID uuid, String novoPassword){
        User existingUser = userDAO.getById(uuid);
        existingUser.setPassword(novoPassword);
        userDAO.updateUser(existingUser);
    }

    public void deletaUser(UUID uuid){
        User existingUser = userDAO.getById(uuid);
        userDAO.deleteUser(existingUser);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

      public void testingFlow() throws IOException {
/*
        httprequest('localhost:8080/users', PUT, '{name: bla, senha: bla, email: bla}')
        User user = httprequest('localhost:8080/users/14372458732947', GET);
        httprequest('localhost:8080/users/14372458732947', POST, '{name: bla2}')
*/
        String nome = "Gustavo";
        String email = "gb@daitan.com";
        String id = "123";
        String password = "123456";

        URL url = new URL("http://localhost:8080/users");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty( "Content-Type", "application/json");
        httpCon.setRequestProperty( "charset", "utf-8");

          // como passar id, email, password, nome
        httpCon.setRequestProperty(nome, nome);
        httpCon.setRequestProperty(email, email);
        httpCon.setRequestProperty(password, password);
        httpCon.setRequestProperty(id, id);



        httpCon.getInputStream();
    }


    @Transactional(Transactional.TxType.NOT_SUPPORTED)
     public User findById(UUID userId) throws ResourceNotFoundException {
        return Optional.ofNullable(userDAO.getById(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
    }
}