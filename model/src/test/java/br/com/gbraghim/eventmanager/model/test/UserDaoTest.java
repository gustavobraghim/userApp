package br.com.gbraghim.eventmanager.model.test;

import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    EntityManager manager;
    @InjectMocks
    UserDAO userDAO = new UserDAO();

    User user;

    @Before
    public void setup(){
        user= new User();
    }

    @Test
    public void testCreateUser() {
        String nome="Gustavo";
        user.setNome(nome);
        String email = "gbraghim@daitangroup.com";
        user.setEmail(email);
        String password = "123456";
        user.setPassword(password);
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));
        userDAO.createUser(user);

        Assert.assertEquals(user.getId(), uuid);
    }

    @Test
    public void testUpdateUser(){
        String nome="Gustavo";
        user.setNome(nome);
        String email = "gbraghim@daitangroup.com";
        user.setEmail(email);
        String password = "123456";
        user.setPassword(password);
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);

        String novoEmail = "lalala@lalala.com";

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));

        user.setEmail(novoEmail);
        userDAO.updateUser(user);

        Assert.assertEquals(user.getId(), uuid);
    }

    @Test
    public void testDeleteUser(){
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);

        when(manager.getTransaction()).thenReturn(Mockito.mock(EntityTransaction.class));
        userDAO.deleteUser(user);

        Assert.assertEquals(user.getId(), uuid);
    }
}
