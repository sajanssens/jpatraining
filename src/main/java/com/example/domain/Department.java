package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;
}
