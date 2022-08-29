package com.example.company.service;

import com.example.company.entity.Car;
import com.example.company.repository.CarRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarValidateService carValidateService;

    @Transactional
    public void validateCarNumber(String carNumber) {
        if(carNumber == null || carNumber.isBlank()) {//|| carRepository.existsByCarNumber(carNumber)
            throw new RuntimeException("Car number is not valid");
        }
    }

    @Transactional
    public void addCars(String carNumber, String manufacturer, String model) throws Exception {
        carRepository.save(Car.builder()
                .carNumber("111111")
                .manufacturer("AAA")
                .model("1")
                .build());

        carRepository.save(Car.builder()
                .carNumber("222222")
                .manufacturer("BBB")
                .model("2")
                .build());

//        if(carNumber == null) {
//            throw new RuntimeException("Car number can't be null");
//        }
        String resultCarNumber = carNumber;
        try {
            carValidateService.validateCarNumber(carNumber);
        } catch (IllegalArgumentException e) {
            resultCarNumber = "default_carnumber";
        }

        carRepository.save(Car.builder()
                .carNumber(resultCarNumber)
                .manufacturer(manufacturer)
                .model(model)
                .build());
    }

}
