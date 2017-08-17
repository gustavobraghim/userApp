package br.com.gbraghim.eventmanager.model;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserDAO {

    private final EntityManager manager;

    public UserDAO () {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("minhaPersistencia");
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

    public void UpdateUser(User user) {
        try {
            manager.getTransaction().begin();
            manager.merge(user);
            manager.getTransaction().commit();
        } catch(Exception ex) {
            manager.getTransaction().rollback();
        }
    }

    public User getByEmail(final String email) {
        return manager.find(User.class, email);
    }

    public User getByName(final String name) {
        return manager.find(User.class, name);
    }

    public User getByPassword(final String password) {
        return manager.find(User.class, password);
    }
}
