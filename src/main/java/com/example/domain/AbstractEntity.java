package com.example.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.IDENTITY;


@MappedSuperclass
public abstract class AbstractEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
}
