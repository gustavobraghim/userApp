package model;

import javax.persistence.*;

public class persistencia {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("minhaPersistencia");
    EntityManager entityManager = factory.createEntityManager();
}
