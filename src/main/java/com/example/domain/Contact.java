package com.example.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
//    @Temporal(value = TemporalType.TIME)
    private LocalDate birthdate;

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

    @ManyToOne(cascade = {PERSIST, MERGE})
    private ParkingSpace parkingSpace;

    @Setter
    @OneToOne(cascade = MERGE, orphanRemoval = true)
    private Car leaseCar;

    public Contact(String firstName, LocalDate date, String email) {
        this.firstName = firstName;
        this.birthdate = date;
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setParkingSpace(ParkingSpace parkeerplaats) {
        ParkingSpace oldOne = this.parkingSpace;
        if (oldOne != null) {
            oldOne.removeContact(this);
        }

        this.parkingSpace = parkeerplaats;
        parkeerplaats.addContact(this);
    }
}
