package com.example.company.repository;

import com.example.company.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findFirstByCompanyName(String companyName);

    @Transactional
    Optional<Company> deleteCompanyById(Long id);
}
