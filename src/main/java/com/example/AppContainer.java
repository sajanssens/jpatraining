package com.example;

import com.example.domain.Contact;
import com.example.domain.ContactService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@SpringBootApplication
public class AppContainer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(AppContainer.class, args);
    }

    public void test() {
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

        Contact piet = service.updateFirstName(bram, "Piet");
        System.out.println(piet);

        Contact contact1 = service.find(1);
        System.out.println(contact1);


    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        test();
    }
}
