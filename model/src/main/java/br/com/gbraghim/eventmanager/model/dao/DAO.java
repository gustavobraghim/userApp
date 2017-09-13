package br.com.gbraghim.eventmanager.model.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class DAO {
    @Bean
    public EntityManager entityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceProvider");
        return factory.createEntityManager();
    }
}
