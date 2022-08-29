package com.example.company.service;

import com.example.company.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarValidateService {
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public void validateCarNumber(String carNumber) {
        if(carNumber == null || carNumber.isBlank() || carRepository.existsByCarNumber(carNumber)) {
            throw new IllegalArgumentException("Car number is not valid");
        }
    }

}
