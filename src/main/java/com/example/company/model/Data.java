package com.example.company.model;

import com.example.company.dto.*;
import com.example.company.dto.worker_dto.SimpleWorkerDTO;
import com.example.company.dto.worker_dto.WorkerDTO;
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
    @JsonProperty("simpleWorker")
    private SimpleWorkerDTO simpleWorkerDTO;
    @JsonProperty("car")
    private CarDTO carDTO;
    @JsonProperty("job")
    private JobDTO jobDTO;
    @JsonProperty("company")
    private CompanyDTO companyDTO;
    @JsonProperty("salary")
    private SalaryDTO salaryDTO;
}
