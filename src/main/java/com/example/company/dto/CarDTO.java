package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String manufacturer;
    private String model;
    private String carNumber;

    public CarDTO(String manufacturer, String model, String carNumber) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.carNumber = carNumber;
    }
}