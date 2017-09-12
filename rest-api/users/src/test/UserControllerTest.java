import br.com.gbraghim.eventmanager.api.controller.UserController;
import br.com.gbraghim.eventmanager.model.domain.User;
import br.com.gbraghim.eventmanager.service.UserService;
import br.com.gbraghim.eventmanager.service.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@RestController
public class UserControllerTest extends TestCase{
    @Mock
    private UserService userService;
    @InjectMocks
    UserController userController = new UserController();
    User user = new User();

    @Autowired
    private ObjectMapper objectMapper;


    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUserTest() throws ResourceNotFoundException {
        // O que eu preciso (cenario)
        String email = "email";
        String nome = "nome";
        String password = "password";
        UUID uuid = UUID.randomUUID();

        // O que quero testar
        user.setPassword(password);
        user.setEmail(email);
        user.setNome(nome);
        user.setId(uuid);
        userController.createUser(user);

        // O que espero que aconteca
        Mockito.verify(userService).registraCliente(uuid, email, password, nome);

    }

    @Test
    public  void editUserNameTest() throws ResourceNotFoundException {
        // O que eu preciso (cenario)
        UUID uuid = UUID.randomUUID();
        String newName = "gustavo";

        // O que quero testar
        user.setId(uuid);
        user.setNome(newName);

        //when(userController.getUser(uuid)).thenReturn(user);
        userController.editUserName(uuid, user);

        User novoUser =  new User();
        novoUser.setNome(newName);
        novoUser.setId(uuid);
        Mockito.verify(userService).alteraNome(uuid, newName);

    }

    @Test
    public  void editUserPasswordTest() throws ResourceNotFoundException {
        // O que eu preciso (cenario)
        UUID uuid = UUID.randomUUID();
        String newPassword = "1234";

        // O que quero testar //SETANDO UM USUARIO PRA PODER REALIZAR MODIFICACOES A PARTIR DOS DADOS SALVOS NELE
        user.setId(uuid);
        user.setPassword(newPassword);

       // when(userController.getUser(uuid)).thenReturn(user);
        userController.editUserPassword(uuid, user);

        User novoUser =  new User();
        novoUser.setPassword(newPassword);
        novoUser.setId(uuid);

        Mockito.verify(userService).alteraPassword(uuid, newPassword);
    }

    @Test
    public void deleteUserTest(){
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);

        userController.deleteUser(uuid);

        Mockito.verify(userService).deletaUser(uuid);
    }

}
