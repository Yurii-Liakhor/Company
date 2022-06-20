package com.example.company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column
    private String manufacturer;
    @Column
    private String model;
}
