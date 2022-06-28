package com.example.company.rest;

import com.example.company.dto.WorkerDTO;
import com.example.company.entity.Car;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Error;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/worker")
public class WorkerRest {

    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public WorkerRest(WorkerRepository workerRepository, CarRepository carRepository) {
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
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

    @GetMapping("/getWorker")
    public Response getWorker(@RequestParam String passport) {
        Worker worker = workerRepository.findWorkerByPassport(passport);
        if(worker == null) {
            return Response.builder()
                    .status(Status.error)
                    .build();
        }
        WorkerDTO workerDTO = modelMapper.map(worker, WorkerDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .workerDTO(workerDTO).build())
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
