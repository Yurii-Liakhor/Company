package com.example.company.rest;

import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeRest {

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
                .data(Data.builder().value("some value").build())
                .build();
    }

    @GetMapping("addEmployee")
    public Response addEmployee() {
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
