package br.com.gbraghim.eventmanager.service;

import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void registraClienteTest(){
        // O que eu preciso (cenario)
        String email = "email";
        String nome = "nome";
        String password = "password";
        UUID uuid = UUID.randomUUID();

        // O que quero testar
        userService.registraCliente(user.getId(), email, password, nome);

        // O que espero que aconteca
        user.setEmail(email);
        user.setNome(nome);
        user.setPassword(password);
        user.setId(uuid);
        Mockito.verify(userDAO).createUser(user);
    }

    @Test
    public void alteraNomeTest(){
        //do que eu preciso? do usuario (email)
        String email = "email";
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
        String email = "gbraghim@bla";
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

    /*@Test
    public void usuarioInvalido(){
         UUID uuid = UUID.randomUUID();
        String email = "gbraghim@bla";
        String password = "123456";
        String newPassword = "aaaaa";
        String nome = "Gustavo";

        User userOriginal = new User();
        userOriginal.setPassword(password);
        userOriginal.setNome(nome);

        when(userDAO.getBuId(uuid)).thenReturn(userOriginal);
        userService.alteraPassword("asdsafsafsa",password);

        User novoUser = new User();
        novoUser.setPassword(newPassword);
        novoUser.setId(uuid);

        Mockito.verify(userDAO).updateUser(novoUser);
    }*/

/*
    @Test
    public List<User> findAll() {
        return userDAO.findAll();
    }
*/
}