package com.example.company.repository;

import com.example.company.entity.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Car findCarByCarNumber(String carNumber);

    @Transactional
    void deleteCarByCarNumber(String carNumber);

    @Transactional
    @Modifying
    @Query("update Car c set c.worker.id = ?2 where c.id = ?1")
    void setCarWorkerById(Long carId, Long workerId);
}
