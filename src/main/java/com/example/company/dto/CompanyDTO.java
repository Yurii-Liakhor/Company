package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String companyName;

    public CompanyDTO(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
