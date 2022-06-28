package com.example.company.rest;

import com.example.company.dto.CarDTO;
import com.example.company.dto.WorkerDTO;
import com.example.company.entity.Car;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarRest {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CarRest(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
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

    @GetMapping("/getCar")
    public Response getCar(@RequestParam String carNumber) {
        Car car = carRepository.findCarByCarNumber(carNumber);
        if(car == null) {
            return Response.builder()
                    .status(Status.error)
                    .build();
        }
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .carDTO(carDTO).build())
                .build();
    }

    @GetMapping("/removeCar")
    public Response removeCar(@RequestParam String carNumber) {
        carRepository.deleteCarByCarNumber(carNumber);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
