package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    private Long id;
    private int salary;
    private String currency;

    public SalaryDTO(int salary, String currency) {
        this.salary = salary;
        this.currency = currency;
    }
}
