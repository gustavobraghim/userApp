/*
package br.com.gbraghim.eventmanager.controller;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class InitService {

    @PostConstruct
    public void main() throws SQLException {
        System.out.println("Testeeeeeeeeeeee");
        //String url = "jdbc:hsqldb:hsqls://localhost/db";
        String sql = "insert into user (email, nome, password) values('marcelo', 'marcelo', 'marcelo')";
        System.out.println("Testeeeeeeeeeeee222222222222222222222222222");

        try (Connection con = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "sa", "");
             PreparedStatement stm = con.prepareStatement(sql)){
            stm.executeUpdate();
        }
        System.out.println("Testeeeeeeeeeeee33333333333333333333333333333");

    }
}
*/
