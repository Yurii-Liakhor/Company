package com.example.company.rest;

import com.example.company.dto.JobDTO;
import com.example.company.entity.Job;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.JobRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/job")
public class JobRest {

    private final JobRepository jobRepository;
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public JobRest(JobRepository jobRepository, WorkerRepository workerRepository) {
        this.jobRepository = jobRepository;
        this.workerRepository = workerRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
                .data(Data.builder().controllerName(this.getClass().getSimpleName()).build())
                .build();
    }

    @PostMapping("/addJob")
    public Response addJob(@RequestBody JobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        jobRepository.save(job);

        return Response.builder()
                .status(Status.done)
                .build();
    }

    @GetMapping("/getJob")
    public Response getJob(@RequestParam Long jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .build();
        }
        JobDTO jobDTO = modelMapper.map(jobOptional.get(), JobDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .jobDTO(jobDTO).build())
                .build();
    }

    @GetMapping("/removeJob")
    public Response removeJob(@RequestParam Long jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(jobOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .build();
        }

        List<Worker> workerList = workerRepository.findWorkersByJobId(jobId);
        if(!workerList.isEmpty()) {
            workerList.forEach(worker -> {
                worker.setJob(null);
                workerRepository.save(worker);
            });
        }
        jobRepository.deleteJobById(jobId);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
