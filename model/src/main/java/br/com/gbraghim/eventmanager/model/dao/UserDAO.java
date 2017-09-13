package br.com.gbraghim.eventmanager.model.dao;

import br.com.gbraghim.eventmanager.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDAO {

    @Autowired
    private EntityManager manager;

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

    public void deleteUser(User user){
        try{
            manager.getTransaction().begin();
            manager.remove(user);
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            manager.getTransaction().rollback();;
        }
    }

    public long checkEmail(String newEmail){
        Query query = manager.createNativeQuery("SELECT COUNT(email) FROM user WHERE email = ?");
        query.setParameter(1, newEmail);
        BigInteger emailCount = (BigInteger)query.getSingleResult();
        return emailCount.longValue();
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