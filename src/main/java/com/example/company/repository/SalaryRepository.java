package com.example.company.repository;

import com.example.company.entity.Personnel;
import com.example.company.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {


}
