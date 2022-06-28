package com.example.company.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(name = "passport_index", columnList = "passport", unique = true)})
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    @Column(unique = true, nullable = false)
    private String passport;
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Job job;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Company company;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.PERSIST)
    private List<Car> cars = new ArrayList<>();

    public Worker(String firstName, String lastName, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id.equals(worker.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", job=" + job +
                ", cars=" + cars +
                '}';
    }
}
