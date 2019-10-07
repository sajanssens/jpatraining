package com.example;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@NamedQuery(name = "findAll", query = "SELECT c FROM Contact c")
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NonNull
    private String firstname;

    @NonNull
    private Date birthdate;


}
