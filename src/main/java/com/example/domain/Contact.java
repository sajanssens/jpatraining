package com.example.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@NamedQuery(name = "findAll", query = "SELECT c FROM Contact c")
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    private long id;

    @NonNull
    @Setter
    private String firstName;

    @NonNull
    private Date birthdate;


}
