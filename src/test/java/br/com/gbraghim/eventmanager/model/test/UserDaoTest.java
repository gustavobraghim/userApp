package br.com.gbraghim.eventmanager.model.test;

import br.com.gbraghim.eventmanager.model.User;
import br.com.gbraghim.eventmanager.model.UserDAO;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testCrudUser() {
        User user = new User();
        user.setNome("Gustavo");
        String email = "gbraghim@daitangroup.com";
        user.setEmail(email);
        user.setPassword("341234");

        UserDAO userDAO = new UserDAO();
        userDAO.UpdateUser(user);

        User byEmail = userDAO.getByEmail(email);

        Assert.assertEquals(user, byEmail);
    }

}
