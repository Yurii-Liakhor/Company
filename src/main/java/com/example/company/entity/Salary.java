package com.example.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryId;
    private int salary;
    @Column(length = 3)
    private String currency;

    @OneToOne
    @JoinColumn(name = "salary_id", referencedColumnName = "personnel_id")
    private Personnel personnel;

    public Salary(int salary, String currency) {
        this.salary = salary;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", salary=" + salary +
                ", currency='" + currency + '\'' +
                '}';
    }
}
