package com.example;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {



    public static void main( String[] args ) {
        EntityManager em = Persistence.createEntityManagerFactory("JPATraining").createEntityManager();
        ContactService service = new ContactService(em);

        Contact bram = new Contact("Bram", new Date());
        service.save(bram);
        service.save(new Contact("A", new Date()));
        service.save(new Contact("X", new Date()));

        long idBram = bram.getId();

        Contact contact = service.find(idBram);
        System.out.println(contact);

        System.out.println("findAll");
        service.findAll().forEach(System.out::println);

        System.out.println("delete 3");
        service.delete(3);

        System.out.println("findAllNamed");
        service.findAllNamed().forEach(System.out::println);
    }
}
