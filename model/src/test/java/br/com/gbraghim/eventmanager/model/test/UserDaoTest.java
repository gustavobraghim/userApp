package br.com.gbraghim.eventmanager.model.test;

import br.com.gbraghim.eventmanager.model.dao.UserDAO;
import br.com.gbraghim.eventmanager.model.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testCrudUser() {
        User user = new User();
        String nome="Gustavo";
        user.setNome(nome);
        String email = "gbraghim@daitangroup.com";
        user.setEmail(email);
        String password = "123456";
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        User byEmail = userDAO.getByEmail(email);

        Assert.assertEquals(user, byEmail);
    }
}
