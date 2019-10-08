package com.example;

import com.example.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
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
                .birthdate(LocalDate.of(1979, 8, 22))
                .email("s.a.janssens@gmail.com")
                .hasDriversLicense(true)
                .resume("dsfhksd fhksdfh ksdjfhksd fhuis fhdiusdh fius hdfiu hsdifushfd ius dhfi fsdisufh uis")
                .build();

        bram.setAddress(new Address("Dorpsstraat 18", "1234 AB", "Het Dorp"));

        System.out.println(bram);
        service.save(bram);
        Contact contact2 = service.find(bram.getId());
        System.out.println(contact2);

        Contact arie = new Contact("Arie", LocalDate.of(2010, 1, 2), "arie@gmail.com");
        service.save(arie);

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
        service.update(piet);

        Department systeembeheer = Department.builder().name("Systeembeheer").build();
        bram.setBossOf(systeembeheer);
        service.update(piet);

        ParkingSpace space = ParkingSpace.of(4);
        bram.setParkingSpace(space);
        service.update(piet);

        ParkingSpace space2 = ParkingSpace.of(6);
        bram.setParkingSpace(space2);
        service.update(piet);

        Car skoda = new Car("Skoda");
        piet.setLeaseCar(skoda);
        service.update(piet);

//        piet.setLeaseCar(null);
//        service.update(piet);
        service.removeCar(piet);

    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        test();
    }
}
