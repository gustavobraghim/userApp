package model;
import model.user;

public class userService {
    private void registraCliente(String nEmail, String nPassword, String nNome){
        user usuario = new user();
        usuario.setEmail(nEmail);
        usuario.setNome(nNome);
        usuario.setPassword(nPassword);
        //Persistencia
    }

    private void alteraNome(String nomeNovo){
        user usuario = new user();
        usuario.setNome(nomeNovo);
        //Persistencia
    }

    private void alteraPassword(String nomePassword){
        user usuario = new user();
        usuario.setPassword(nomePassword);
        //Persistencia
    }


}
