package com.example.company.rest;

import com.example.company.dto.SalaryDTO;
import com.example.company.dto.worker_dto.SimpleWorkerDTO;
import com.example.company.dto.worker_dto.WorkerDTO;
import com.example.company.entity.Job;
import com.example.company.entity.Salary;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.JobRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/worker")
public class WorkerRest {

    private final WorkerRepository workerRepository;
    private final JobRepository jobRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public WorkerRest(WorkerRepository workerRepository, JobRepository jobRepository, CarRepository carRepository) {
        this.workerRepository = workerRepository;
        this.jobRepository = jobRepository;
        this.carRepository = carRepository;
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
        log.info("addWorker");
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

    @Cacheable(value = "worker", key = "#passport")
    @GetMapping("/getWorker")
    public Response getWorker(@RequestParam String passport) {
        log.info("getWorker");
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

    @GetMapping("/getSimpleWorker")
    public Response getSimpleWorker(@RequestParam String passport) {
        log.info("getSimpleWorker");
        Optional<Worker> workerOptional = workerRepository.findSimpleWorkerByPassport(passport);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker is empty"
                    })
                    .build();
        }
        SimpleWorkerDTO workerDTO = modelMapper.map(workerOptional.get(), SimpleWorkerDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .simpleWorkerDTO(workerDTO).build())
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

    @CacheEvict(value = "worker", key = "#passport")
    @GetMapping("/removeWorker")
    public Response removeWorker(@RequestParam String passport) {
        log.info("removeWorker");

        Optional<Worker> workerOptional = workerRepository.findWorkerByPassport(passport);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker wasn't found"
                    })
                    .build();
        }

        workerOptional.get().getCars().forEach(car -> {
            car.setWorker(null);
        });

        carRepository.saveAll(workerOptional.get().getCars());

        workerRepository.deleteWorkerByPassport(passport);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
