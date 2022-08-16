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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private String model;
    @Column(unique = true, nullable = false)
    private String carNumber;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Car(String manufacturer, String model, String carNumber) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (!getManufacturer().equals(car.getManufacturer())) return false;
        if (!getModel().equals(car.getModel())) return false;
        return getCarNumber().equals(car.getCarNumber());
    }

    @Override
    public int hashCode() {
        int result = getManufacturer().hashCode();
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getCarNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
