package br.com.gbraghim.eventmanager.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class includeData {
    public static void main(String[] args) throws SQLException {
        //String url = "jdbc:hsqldb:hsqls://localhost/db";
        String sql = "insert into user values(walter, walter, walter)";
        try (Connection con = DriverManager.getConnection("jdbc:hsqldb:file:/opt/db/testdb", "sa", "");
             PreparedStatement stm = con.prepareStatement(sql)){
            stm.executeUpdate();
        }
    }
}