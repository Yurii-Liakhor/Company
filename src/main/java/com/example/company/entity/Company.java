package com.example.company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    @Column
    private String companyName;
}
