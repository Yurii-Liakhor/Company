package com.example.company.repository;

import com.example.company.entity.Company;
import com.example.company.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {


}
