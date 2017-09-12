package br.com.gbraghim.eventmanager.model.dao;

import br.com.gbraghim.eventmanager.model.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventDAO {

    @Autowired
    private EntityManager manager;

    public void createEvent(Event event){
        try{
            manager.getTransaction().begin();
            manager.persist(event);
            manager.getTransaction().commit();
        }catch (Exception ex){
            manager.getTransaction().rollback();
        }
    }

    public void updateEvent (Event event){
        try {
            manager.getTransaction().begin();
            manager.merge(event);
            manager.getTransaction().commit();

        }catch (Exception ex){
            manager.getTransaction().rollback();
        }
    }

    public void deleteEvent(Event event){
        try {
            manager.getTransaction().begin();
            manager.remove(event);
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            manager.getTransaction().rollback();
        }
    }

    public Event getById(final int id) {
        return manager.find(Event.class, id);
    }

    public Event getByData(final int id) {

        return manager.find(Event.class, id);

    }

    public List<Event> findAll() {
        String jpql = "FROM " + Event.class.getSimpleName();
        List resultList = manager.createQuery(jpql).getResultList();
        return Optional.ofNullable(resultList)
                .orElse(new ArrayList());
    }
}

