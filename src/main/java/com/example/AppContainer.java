package com.example;

import com.example.domain.Address;
import com.example.domain.Contact;
import com.example.domain.ContactService;
import com.example.domain.Department;
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

        Date date = new Date();
        System.out.println("DATE:");
        System.out.println(date);

        Contact bram = Contact.builder()
                .firstName("Bram")
                .birthdate(new Date())
                .email("s.a.janssens@gmail.com")
                .hasDriversLicense(true)
                .resume("dsfhksd fhksdfh ksdjfhksd fhuis fhdiusdh fius hdfiu hsdifushfd ius dhfi fsdisufh uis")
                .build();

        bram.setAddress(new Address("Dorpsstraat 18", "1234 AB", "Het Dorp"));

        System.out.println(bram);
        service.save(bram);

        Contact contact2 = service.find(bram.getId());
        System.out.println(contact2);
        service.save(new Contact("A", new Date(), "s.a.janssens2@gmail.com"));
        service.save(new Contact("X", new Date(), "s.a.janssens3@gmail.com"));

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

        Department kenniscentrum = Department.builder().name("Kenniscentrum").build();
        bram.setBossOf(kenniscentrum);
        service.update(bram);

        Department systeembeheer = Department.builder().name("Systeembeheer").build();
        bram.setBossOf(systeembeheer);
        service.update(bram);

    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        test();
    }
}
