package com.example.company.rest.car;

import com.example.company.repository.CarRepository;
import com.example.company.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@AutoConfigureTestDatabase
class CarServiceTest {
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
        carRepository.deleteAll();
        System.out.println("beforeEach 2");
    }

    @Test
    void testThrowsExceptionAndRollback() throws Exception {
        System.out.println("testThrowsExceptionAndRollback");
        carService.addCars(null, "new_manufacturer", "new_model");
//        assertThrows(Exception.class, () -> carService.addCars(null, "new_manufacturer", "new_model"));
//        assertEquals(0, carRepository.count());
    }

//    @Test
//    void testThrowsNullPointerExceptionAndRollback() {
//        assertThrows(Exception.class, () -> carService.addCars("new_carnumber", "new_manufacturer", "new_model"));
//        assertEquals(0, carRepository.count());
//    }

}