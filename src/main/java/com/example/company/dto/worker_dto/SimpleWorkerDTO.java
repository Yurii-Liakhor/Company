package com.example.company.dto.worker_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleWorkerDTO {
    private String firstName;
    private String lastName;

    public SimpleWorkerDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}