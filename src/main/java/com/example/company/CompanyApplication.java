package com.example.company;

import com.example.company.entity.*;
import com.example.company.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Bean
	public CommandLineRunner initWorkers(WorkerRepository repository) {
		return (args) -> {
			log.info("initWorkers");

			List<Worker> workerList = Arrays.asList(
					new Worker("David", "Gilmour"),
					Worker.builder().firstName("Юрій").lastName("Ляхор").salary(new Salary(3000, "USD")).build(),
					new Worker("Vitaliy", "Kim"),
					new Worker("David", "Palmer"),
					new Worker("Патрон", "Пес")
			);

			repository.saveAll(workerList);
		};
	}

	@Bean
	public CommandLineRunner initCars(CarRepository carRepository, WorkerRepository workerRepository) {
		return (args) -> {
			log.info("initCars");

			Worker worker1 = workerRepository.findFirstByFirstName("Юрій");
			List<Car> carList = Arrays.asList(
					new Car("Tesla", "X"),
					new Car("Tesla", "Y")
			);
			carList.get(0).setWorker(worker1);
			carList.get(1).setWorker(worker1);

			carRepository.saveAll(carList);
		};
	}

	@Bean
	public CommandLineRunner initJobs(JobRepository jobRepository, WorkerRepository workerRepository) {
		return (args) -> {
			log.info("initJobs");

			List<Job> jobList = Arrays.asList(
					new Job("Programmer"),
					new Job("Military"),
					new Job("Singer")
			);
			jobRepository.saveAll(jobList);

			Worker worker1 = workerRepository.findFirstByFirstName("Юрій");
			worker1.setJob(jobList.get(0));

			Worker worker2 = workerRepository.findFirstByFirstName("Патрон");
			worker2.setJob(jobList.get(1));

			Worker worker3 = workerRepository.findFirstByFirstName("David");
			worker3.setJob(jobList.get(2));

			workerRepository.save(worker1);
			workerRepository.save(worker2);
			workerRepository.save(worker3);
		};
	}

	@Bean
	public CommandLineRunner initCompanies(CompanyRepository companyRepository, WorkerRepository workerRepository) {
		return (args) -> {
			log.info("initCompanies");

			List<Company> companyList = Arrays.asList(
					new Company("SkyService"),
					new Company("EPAM"),
					new Company("Luxoft"),
					new Company("GlobalLogic"),
					new Company("Ukrainian Armed Forces")
			);

			companyRepository.saveAll(companyList);

			Worker worker1 = workerRepository.findFirstByFirstName("Юрій");
			worker1.setCompany(companyList.get(0));

			Worker worker2 = workerRepository.findFirstByFirstName("Патрон");
			worker2.setCompany(companyList.get(4));

			workerRepository.save(worker1);
			workerRepository.save(worker2);
		};
	}
}