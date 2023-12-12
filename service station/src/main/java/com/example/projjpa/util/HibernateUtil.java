package com.example.projjpa.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
public class HibernateUtil {



    public static SessionFactory getCurrentSessionFromJPA() {
        // JPA and Hibernate SessionFactory example
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa-tutorial");
        EntityManager entityManager = emf.createEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SessionFactory factory = session.getSessionFactory();
        return factory;
    }

}
