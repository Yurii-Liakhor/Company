package com.example.company;

import com.example.company.entity.Car;
import com.example.company.entity.Job;
import com.example.company.entity.Worker;
import com.example.company.entity.Salary;
import com.example.company.repository.CarRepository;
import com.example.company.repository.JobRepository;
import com.example.company.repository.SalaryRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootApplication
public class CompanyApplication {

	public static void main(String[] args) {
		log.info("test log");
		SpringApplication.run(CompanyApplication.class, args);
		log.info("test log 2");
	}

	@PostConstruct
	public void init() {
		log.info("test log 3");
	}

	@Bean
	public CommandLineRunner demo(WorkerRepository repository) {
		return (args) -> {
			log.info("demo");

			Worker worker1 = new Worker("Yurii", "Liakhor");
			Salary salary1 = new Salary(999999999, "UAH");
			worker1.setSalary(salary1);
			salary1.setWorker(worker1);

			repository.save(new Worker("Chloe", "O'Brian"));
			repository.save(worker1);
			repository.save(new Worker("Kim", "Bauer"));
			repository.save(new Worker("David", "Palmer"));
			repository.save(new Worker("Michelle", "Dessler"));
		};
	}

	@Bean
	public CommandLineRunner demo2(CarRepository carRepository, WorkerRepository workerRepository) {
		return (args) -> {
			log.info("demo2");

			Car car1 = new Car("Tesla", "X");
			Car car2 = new Car("Tesla", "Y");

			Worker worker1 = workerRepository.findFirstByFirstName("Yurii");
			car1.setWorker(worker1);
			car2.setWorker(worker1);

			carRepository.save(car1);
			carRepository.save(car2);
		};
	}

	@Bean
	public CommandLineRunner demo3(JobRepository jobRepository, WorkerRepository workerRepository) {
		return (args) -> {
			log.info("demo3");

			Job job1 = new Job("Programmer");

			jobRepository.save(job1);

			Worker worker1 = workerRepository.findFirstByFirstName("Yurii");
//			List<Worker> workerList = new ArrayList<>();
//			workerList.add(worker1);
//			job1.setWorkers(workerList);
			worker1.setJob(job1);

//			jobRepository.save(job1);
			workerRepository.save(worker1);
		};
	}
}