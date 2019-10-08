package com.example.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Parkingspace")
@NoArgsConstructor
public class ParkingSpace extends AbstractEntity {
    private int nr;

    @OneToMany(mappedBy = "parkingSpace")
    private List<Contact> contacts = new ArrayList<>();

    private ParkingSpace(int nr) {
        this.nr = nr;
    }

    public static ParkingSpace of(int n) {
        return new ParkingSpace(n);
    }

    public void addContact(Contact c) {
//        if (contacts == null) {
//            contacts = new ArrayList<>();
//        }
        contacts.add(c);
    }

    public void removeContact(Contact c) {
        contacts.remove(c);
    }
}

