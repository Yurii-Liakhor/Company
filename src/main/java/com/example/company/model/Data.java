package com.example.company.model;

import com.example.company.dto.WorkerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @JsonProperty("worker")
    private WorkerDTO workerDTO;
}
