package br.com.gbraghim.eventmanager.controller;
import br.com.gbraghim.eventmanager.model.User;
import br.com.gbraghim.eventmanager.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class EditDataController {
    public static final String EDIT_PAGE = "edit";

    @Autowired
    private UserDAO userDAO;
    User user = new User();

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String tryAgain() {
        return EDIT_PAGE;
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public String edit(@RequestParam String name, @RequestParam String password) throws SQLException {
        user.setNome(name);
        user.setPassword(password);
        userDAO.updateUser(user);

        return "welcome";
    }
}
