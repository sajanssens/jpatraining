package com.example.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class ParkingSpace extends AbstractEntity {
    private int nr;

    @OneToMany(mappedBy = "parkeerplaats")
    private List<Contact> contact;

    private ParkingSpace(int nr) {
        this.nr = nr;
    }

    public static ParkingSpace of(int n) {
        return new ParkingSpace(n);
    }
}

