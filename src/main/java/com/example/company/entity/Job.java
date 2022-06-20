package com.example.company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    @Column
    private String jobName;
}
