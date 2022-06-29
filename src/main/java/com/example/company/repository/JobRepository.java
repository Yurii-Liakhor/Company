package com.example.company.repository;

import com.example.company.entity.Job;
import com.example.company.entity.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Optional<Job> findJobById(long id);
    @Transactional
    void deleteJobById(Long jobId);
}
