package com.example.company.repository;

import com.example.company.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {

}
