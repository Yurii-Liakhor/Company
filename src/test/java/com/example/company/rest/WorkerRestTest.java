package com.example.company.rest;

import com.example.company.dto.worker_dto.WorkerDTO;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WorkerRestTest {
    @Autowired
    private WorkerRest workerRest;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addWorker() throws JsonProcessingException {
        WorkerDTO workerDTO = new WorkerDTO("test_name", "test_surname", "test_passport");
        Response response = workerRest.addWorker(workerDTO);

        System.out.println(objectMapper.writeValueAsString(response));

        assertThat(response.getStatus()).isEqualTo(Status.done);
    }

    @Test
    public void getWorker() throws JsonProcessingException {
        Response response = workerRest.getWorker("test_passport");//KY212432

        System.out.println(objectMapper.writeValueAsString(response));

        assertThat(response.getStatus()).isEqualTo(Status.done);
    }
}
