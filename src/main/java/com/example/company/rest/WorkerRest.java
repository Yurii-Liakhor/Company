package com.example.company.rest;

import com.example.company.dto.SalaryDTO;
import com.example.company.dto.WorkerDTO;
import com.example.company.entity.Job;
import com.example.company.entity.Salary;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.JobRepository;
import com.example.company.repository.SalaryRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/worker")
public class WorkerRest {

    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final JobRepository jobRepository;
    private final SalaryRepository salaryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public WorkerRest(WorkerRepository workerRepository, CarRepository carRepository, JobRepository jobRepository, SalaryRepository salaryRepository) {
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
        this.jobRepository = jobRepository;
        this.salaryRepository = salaryRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
                .data(Data.builder().controllerName(this.getClass().getSimpleName()).build())
                .build();
    }

    @PostMapping("/addWorker")
    public Response addWorker(@RequestBody WorkerDTO workerDTO) {
        Worker worker = modelMapper.map(workerDTO, Worker.class);
        workerRepository.save(worker);

        return Response.builder()
                .status(Status.done)
                .build();
    }

    @GetMapping("/addJobToWorker")
    public Response addJobToWorker(@RequestParam Long workerId, @RequestParam Long jobId) {
        Optional<Worker> workerOptional = workerRepository.findWorkerById(workerId);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker doesn't exist"
                    })
                    .build();
        }
        Optional<Job> jobOptional = jobRepository.findJobById(jobId);
        if(jobOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "job doesn't exist"
                    })
                    .build();
        }

        workerOptional.get().setJob(jobOptional.get());
        workerRepository.save(workerOptional.get());
        return Response.builder()
                .status(Status.done)
                .build();
    }

    @GetMapping("/getWorker")
    public Response getWorker(@RequestParam String passport) {
        Optional<Worker> workerOptional = workerRepository.findWorkerByPassport(passport);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker is empty"
                    })
                    .build();
        }
        WorkerDTO workerDTO = modelMapper.map(workerOptional.get(), WorkerDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .workerDTO(workerDTO).build())
                .build();
    }

    @GetMapping("/getSalary")
    public Response getSalary(@RequestParam Long workerId) {
        Optional<Worker> workerOptional = workerRepository.findWorkerById(workerId);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker is empty"
                    })
                    .build();
        }

        Salary salary = workerOptional.get().getSalary();
        if(salary == null) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "salary is not set"
                    })
                    .build();
        }

        SalaryDTO salaryDTO = modelMapper.map(salary, SalaryDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .salaryDTO(salaryDTO).build())
                .build();
    }

    @GetMapping("/removeWorker")
    public Response removeWorker(@RequestParam String passport) {
        workerRepository.deleteWorkerByPassport(passport);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
