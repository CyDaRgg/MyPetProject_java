package edu.javacourse.register.manager;


import edu.javacourse.register.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class PersonManager {

    public static void main(String[] args) {
        sessionExample();

        jpaExample();

    }

    private static void jpaExample() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("persistence");

        EntityManager em =emf.createEntityManager(); // типо как Session для hibernate

        em.getTransaction().begin();

        Person p = new Person();
        p.setFirstName("Алексей");
        p.setLastName("Федоров");

        em.persist(p);
        System.out.println(p.getPersonId());

        em.getTransaction().commit();
        em.close();


        em=emf.createEntityManager();
        List list = em.createQuery("FROM Person").getResultList();//JPAQL

        list.forEach(p1 -> System.out.println(p1));
        em.close();

    }


    private static void sessionExample() {
        SessionFactory sf = buildSessionFactory();

        System.out.println();
        System.out.println();
        System.out.println();

        Session session =sf.openSession();

        session.getTransaction().begin(); //начало транзакции(начало последовательности действий)

        Person p = new Person();
        p.setFirstName("Василий");
        p.setLastName("Сидоров");
        //Serializable id = session.save(p); //Serializable - это интерфейс в Java, который используется для обозначения классов, объекты которых могут быть сериализованы.(в поток байтов превратиться)
        Long id = (Long)session.save(p);
        System.out.println(id);

        session.getTransaction().commit(); // если все прошло норм, то совершаем транзакцию(в идеале там должен был быть try catch и rollback если беда
        session.close();


        //нету session.getTransaction().begin и session.getTransaction().commit так как тут режим readOnly
        Session session2 = sf.openSession();
        Person person = session2.get(Person.class, id);
        System.out.println(person);
        session2.close();


        Session session3 = sf.openSession();
        List<Person> list = session3.createQuery("FROM Person", Person.class).list();
        list.forEach(p1-> System.out.println(p1));

        session3.close();
    }


    //конфигурация hibernate с использованием классов, которые он предлагает.
    private static SessionFactory buildSessionFactory() { //типо как poll коннектов, но не совсем
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //StandardServiceRegistry представляет собой реестр сервисов Hibernate
                    .configure("hibernate.cfg.xml").build(); //создаем по конфигурационному файлу класс, который как инструкция для hibernate что и как делать

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build(); //Metadata представляет собой метаданные о сущностях приложения, таких как классы, атрибуты и их отображение на таблицы в базе данных.

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
