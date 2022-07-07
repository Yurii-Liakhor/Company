package com.example.company.rest;

import com.example.company.dto.CompanyDTO;
import com.example.company.dto.JobDTO;
import com.example.company.entity.Car;
import com.example.company.entity.Company;
import com.example.company.entity.Job;
import com.example.company.entity.Worker;
import com.example.company.model.Data;
import com.example.company.model.Response;
import com.example.company.model.Status;
import com.example.company.repository.CarRepository;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.JobRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/company")
public class CompanyRest {

    private final CompanyRepository companyRepository;
    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CompanyRest(CompanyRepository companyRepository, WorkerRepository workerRepository, CarRepository carRepository) {
        this.companyRepository = companyRepository;
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/apiTest")
    public Response apiTest() {
        return Response.builder()
                .status(Status.done)
                .data(Data.builder().controllerName(this.getClass().getSimpleName()).build())
                .build();
    }

    @PostMapping("/addCompany")
    public Response addCompany(@RequestBody CompanyDTO companyDTO) {
        log.info("companyDTO {}", companyDTO);
        Company company = modelMapper.map(companyDTO, Company.class);
        companyRepository.save(company);

        return Response.builder()
                .status(Status.done)
                .build();
    }

    @Cacheable(value = "company", key = "#companyId")
    @GetMapping("/getCompany")
    public Response getCompany(@RequestParam Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "company wasn't found"
                    })
                    .build();
        }
        CompanyDTO companyDTO = modelMapper.map(companyOptional.get(), CompanyDTO.class);
        return Response.builder()
                .status(Status.done)
                .data(Data.builder()
                        .companyDTO(companyDTO).build())
                .build();
    }

    @CacheEvict(value = "company", key = "#companyId")
    @GetMapping("/removeCompany")
    public Response removeCompany(@RequestParam Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isEmpty()) {
            return Response.builder()
                    .status(Status.error)
                    .errors(new String[]{
                            "company wasn't found"
                    })
                    .build();
        }

        List<Worker> workerList = workerRepository.findWorkersByCompanyId(companyId);
        log.info("workerList size: {}", workerList.size());
        if(!workerList.isEmpty()) {
            workerList.forEach(worker -> {
                worker.setCompany(null);
                workerRepository.save(worker);
            });
        }

        List<Car> carList = carRepository.findCarsByCompanyId(companyId);
        log.info("carList size: {}", carList.size());
        if(!carList.isEmpty()) {
            carList.forEach(car -> {
                car.setCompany(null);
                carRepository.save(car);
            });
        }

        companyRepository.deleteCompanyById(companyId);
        return Response.builder()
                .status(Status.done)
                .build();
    }
}
