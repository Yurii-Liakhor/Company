package com.example.company.dto.worker_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date birthDate;

    public WorkerDTO(String firstName, String lastName, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    public WorkerDTO(String firstName, String lastName, String middleName, String passport, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passport = passport;
        this.birthDate = birthDate;
    }
}