package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int salary;
    @Column(nullable = false, length = 3)
    private String currency;

    @OneToOne(mappedBy = "salary")
    private Worker worker;

    public Salary(int salary, String currency) {
        this.salary = salary;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salary)) return false;

        Salary salary1 = (Salary) o;

        if (getSalary() != salary1.getSalary()) return false;
        return getCurrency().equals(salary1.getCurrency());
    }

    @Override
    public int hashCode() {
        int result = getSalary();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", salary=" + salary +
                ", currency='" + currency + '\'' +
                '}';
    }
}
