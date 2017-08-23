package br.com.gbraghim.eventmanager.model.dao;

import br.com.gbraghim.eventmanager.model.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDAO {
    private final EntityManager manager;

    public UserDAO () {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceProvider");
        manager = factory.createEntityManager();
    }

    public void createUser(User user) {
        try {
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        } catch(Exception ex) {
            manager.getTransaction().rollback();
        }
    }

    public void updateUser(User user) {
        try {
            manager.getTransaction().begin();
            manager.merge(user);
            manager.getTransaction().commit();
        } catch(Exception ex) {
            manager.getTransaction().rollback();
        }
    }


    public int checkEmail(final String newEmail){
        manager.createNativeQuery("SELECT newEmail("+newEmail+") FROM user)");
//https://www.tutorialspoint.com/hibernate/hibernate_native_sql.htm
        return 0; //retornar o count. se o count for !=0 já tem alguém com o email digitado cadastrado
    };

    public User getByEmail(final UUID email) {
        return manager.find(User.class, email);
    }

    public User getByName(final String name) {
        return manager.find(User.class, name);
    }

    public User getByPassword(final String password) {
        return manager.find(User.class, password);
    }

    public User getById(final UUID uuid) {
        return manager.find(User.class, uuid);
    }


    public List<User> findAll() {
        String jpql = "FROM " + User.class.getSimpleName();
        return Optional.ofNullable(manager.createQuery(jpql).getResultList())
                .orElse(new ArrayList());
    }
}