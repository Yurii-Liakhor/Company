package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "company")
    private List<Worker> workers = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Car> cars = new ArrayList<>();

    public Company(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company c = (Company) o;
        return Objects.equals(companyName, c.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
