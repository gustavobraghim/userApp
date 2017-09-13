import br.com.gbraghim.eventmanager.model.domain.User;
import br.gbraghim.eventmanager.api.application.UsersApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {UsersApplication.class})
public class UserApiTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testeUser(){
        String url = "/users";
        List forObject = this.restTemplate.getForObject(url, List.class);
        Assert.assertEquals(0, forObject.size());

        User userToCreate = new User();
        UUID uuid = UUID.randomUUID();
        userToCreate.setId(uuid);
        userToCreate.setEmail("teste@example.com");
        userToCreate.setNome("Gustavo");
        userToCreate.setPassword("abc123");
        ResponseEntity<User> userResponseEntity = this.restTemplate.postForEntity(url, userToCreate, User.class);

        Assert.assertNotNull(userResponseEntity.getBody().getId());
        Assert.assertEquals(userToCreate.getId(), userResponseEntity.getBody().getId());
        Assert.assertNotNull(userResponseEntity.getBody().getEmail());
        Assert.assertEquals(userToCreate.getEmail(), userResponseEntity.getBody().getEmail());
        Assert.assertNotNull(userResponseEntity.getBody().getNome());
        Assert.assertEquals(userToCreate.getNome(), userResponseEntity.getBody().getNome());
    }

    @Test
    public void testeAlteraNome(){
        User userToCreate = new User();
        UUID uuid = UUID.randomUUID();
        userToCreate.setId(uuid);
        userToCreate.setEmail("teste@example.com");
        userToCreate.setNome("Gustavo");
        userToCreate.setPassword("abc123");
        String novoNome = "AAAAAA";
        userToCreate.setNome(novoNome);

        Assert.assertEquals(novoNome,userToCreate.getNome());
    }

    @Test
    public void testeAlteraEmail(){
        User userToCreate = new User();
        UUID uuid = UUID.randomUUID();
        userToCreate.setId(uuid);
        userToCreate.setEmail("teste@example.com");
        userToCreate.setNome("Gustavo");
        userToCreate.setPassword("abc123");
        String novoEmail = "AAAAAA@hotmail.com";
        userToCreate.setEmail(novoEmail);
        Assert.assertEquals(novoEmail,userToCreate.getEmail());
    }

    @Test
    public void testeAlteraPassword(){
        User userToCreate = new User();
        UUID uuid = UUID.randomUUID();
        userToCreate.setId(uuid);
        userToCreate.setEmail("teste@example.com");
        userToCreate.setNome("Gustavo");
        userToCreate.setPassword("abc123");
        String novoPassword = "1a2b3c";
        userToCreate.setPassword(novoPassword);

        Assert.assertEquals(novoPassword,userToCreate.getPassword());
    }

    @Test
    public void testeDeleteUser(){
        String url = "/users";
        List forObject1 = this.restTemplate.getForObject(url, List.class);
        int zeroUsers = forObject1.size(); //nenhum usuario cadastrado ainda
        Assert.assertEquals(0, zeroUsers);

        User userToCreate = new User();
        UUID uuid = UUID.randomUUID();
        userToCreate.setId(uuid);
        userToCreate.setEmail("teste@example.com");
        userToCreate.setNome("Gustavo");
        userToCreate.setPassword("abc123");

        Object auxiliar1=this.restTemplate.postForObject(url, userToCreate, Object.class);

        List forObject2 = this.restTemplate.getForObject(url, List.class);
        int oneUser = forObject2.size();
        Assert.assertEquals(1, oneUser); //agora tem um user cadastrado

        String deleteUrl = url + "/" + uuid;
        this.restTemplate.delete(deleteUrl);

        Assert.assertEquals(0, this.restTemplate.getForObject(url, List.class).size());
    }
}
