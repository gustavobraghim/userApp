package br.com.gbraghim.eventmanager.service;

import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase{

    @Mock //estudar mockito
    private UserDAO userDAO;
    @InjectMocks
    UserService userService = new UserService();
    User user = new User();

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registraClienteTest() throws ResourceNotFoundException {
        // O que eu preciso (cenario)
        String email = "email";
        String nome = "nome";
        String password = "password";
        String senhaHex = null;
        UUID uuid = UUID.randomUUID();

        try {
            MessageDigest messageDigest1 = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = messageDigest1.digest(password.getBytes("UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : messageDigest) {
                stringBuilder.append(String.format("%02X", 0xFF & b));
            }

            senhaHex = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // O que quero testar
        userService.registraCliente(uuid, email, senhaHex, nome);

        // O que espero que aconteca
        user.setEmail(email);
        user.setNome(nome);
        user.setPassword(senhaHex);
        user.setId(uuid);
        Mockito.verify(userDAO).createUser(user);
    }

    @Test
    public void alteraNomeTest(){
        //do que eu preciso? do usuario (email)
        String nome = "Gustavo";
        String nomeNovo = "Marcelo";
        UUID uuid = UUID.randomUUID();
        User userOriginal = new User();

        //o que eu quero testar? a alteração de nome
        userOriginal.setId(uuid);
        userOriginal.setNome(nome);

        when(userDAO.getById(uuid)).thenReturn(userOriginal);
        userService.alteraNome(uuid,nomeNovo);

        User novoUser =  new User();
        novoUser.setNome(nomeNovo);
        novoUser.setId(uuid);

        //o que eu espero que aconteça? que o nome seja alterado
          Mockito.verify(userDAO).updateUser(novoUser);
    }

    @Test
    public void alteraPasswordTest(){
        String password = "123456";
        String newPassword = "abcdef";
        UUID uuid = UUID.randomUUID();

        User userOriginal = new User();

        userOriginal.setId(uuid);
        userOriginal.setPassword(password);

        when(userDAO.getById(uuid)).thenReturn(userOriginal);
        userService.alteraPassword(uuid, newPassword);

        User novoUser = new User();
        novoUser.setPassword(newPassword);
        novoUser.setId(uuid);

        //o que eu espero que aconteça? o password seja alterado
        Mockito.verify(userDAO).updateUser(novoUser);
    }

    @Test
    public void deleteUserTest(){
        UUID uuid = UUID.randomUUID();

        User userOriginal = new User();
        userOriginal.setId(uuid);

        User novoUser = new User();
        novoUser.setId(uuid);

        when(userDAO.getById(uuid)).thenReturn(userOriginal);
        userService.deletaUser(uuid);

        Mockito.verify(userDAO).deleteUser(novoUser);
    }


}