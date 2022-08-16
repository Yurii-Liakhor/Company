package com.example.company.rest;

import com.example.company.dto.CarDTO;
import com.example.company.entity.Car;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/car")
public class CarRest {
    private final CarRepository carRepository;
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CarRest(CarRepository carRepository, WorkerRepository workerRepository) {
        this.carRepository = carRepository;
        this.workerRepository = workerRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
                .data(Data.builder().controllerName(this.getClass().getSimpleName()).build())
                .build();
    }

    @PostMapping("/addCar")
    public Response addCar(@RequestBody CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        carRepository.save(car);

        return Response.builder()
                .status(Status.done)
                .build();
    }

    @GetMapping("/addCarToWorker")
    public Response addCarToWorker(@RequestParam String carNumber, @RequestParam Long workerId) {
        Optional<Car> carOptional = carRepository.findCarByCarNumber(carNumber);
        if(carOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "car doesn't exist"
                    })
                    .build();
        }
        Optional<Worker> workerOptional = workerRepository.findWorkerById(workerId);
        if(workerOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "worker doesn't exist"
                    })
                    .build();
        }

        carOptional.get().setWorker(workerOptional.get());
        carRepository.save(carOptional.get());
        return Response.builder()
                .status(Status.done)
                .build();
    }

    @Cacheable(value = "car", key = "#carNumber")
    @GetMapping("/getCar")
    public Response getCar(@RequestParam String carNumber) {
        Optional<Car> optionalCar = carRepository.findCarByCarNumber(carNumber);
        if(optionalCar.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "no cars were found"
                    })
                    .build();
        }
        CarDTO carDTO = modelMapper.map(optionalCar.get(), CarDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .carDTO(carDTO).build())
                .build();
    }

    @CacheEvict(value = "car", key = "#carNumber")
    @GetMapping("/removeCar")
    public Response removeCar(@RequestParam String carNumber) {
        carRepository.deleteCarByCarNumber(carNumber);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
