package com.example.company.repository;

import com.example.company.entity.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Long> {
    Personnel findPersonnelByPersonnelId(long id);
    List<Personnel> findAllByFirstName(String firsName);
}
