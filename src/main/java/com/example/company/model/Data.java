package com.example.company.model;

import com.example.company.dto.CarDTO;
import com.example.company.dto.CompanyDTO;
import com.example.company.dto.JobDTO;
import com.example.company.dto.WorkerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String controllerName;
    @JsonProperty("worker")
    private WorkerDTO workerDTO;
    @JsonProperty("car")
    private CarDTO carDTO;
    @JsonProperty("job")
    private JobDTO jobDTO;
    @JsonProperty("company")
    private CompanyDTO companyDTO;
}
