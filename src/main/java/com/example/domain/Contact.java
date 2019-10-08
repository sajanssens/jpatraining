package com.example.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Contactpersoon")
@NamedQuery(name = "findAll", query = "SELECT c FROM Contact c")
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    private long id;

    //    @NonNull
    @Setter
    @Column(name = "voornaam", length = 50, nullable = false)
    private String firstName;

    @NonNull
    @Temporal(value = TemporalType.TIME)
    private Date birthdate;

    @NonNull
    @Column(unique = true, length = 255)
    private String email;

    @Setter
    private boolean hasDriversLicense;

    @Lob
    @Basic(fetch = LAZY)
    private String resume;

    @Lob
    private byte[] picture;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Embedded
    private Address address;

    @Setter
    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "DEPT_BOSS_ID")
    private Department bossOf;

    @Setter
    @ManyToOne(cascade = MERGE)
    private ParkingSpace parkeerplaats;

    public Contact(String firstName, Date date, String email) {
        this.firstName = firstName;
        this.birthdate = date;
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
