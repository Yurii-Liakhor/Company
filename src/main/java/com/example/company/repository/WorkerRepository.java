package com.example.company.repository;

import com.example.company.entity.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {

    Optional<Worker> findWorkerById(long id);

    List<Worker> findAllByFirstName(String firsName);

    Worker findFirstByFirstName(String firstName);

    Optional<Worker> findWorkerByPassport(String passport);

    List<Worker> findWorkersByJobId(Long jobId);
    List<Worker> findWorkersByCompanyId(Long jobId);

    @Transactional
    void deleteWorkerByPassport(String passport);

    @Query(value = "DELETE FROM Worker w WHERE w.passport = :passport")
    void deleterByPassport(String passport);
}
