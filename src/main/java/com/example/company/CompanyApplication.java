package com.example.company;

import com.example.company.entity.Personnel;
import com.example.company.entity.Salary;
import com.example.company.repository.PersonnelRepository;
import com.example.company.repository.SalaryRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

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
	public CommandLineRunner demo(PersonnelRepository repository) {
		return (args) -> {
			// save a few customers
			Personnel personnel1 = new Personnel("Yurii", "Liakhor");
			Salary salary1 = new Salary(999999999, "UAH");
			personnel1.setSalary(salary1);

			salary1.setPersonnel(personnel1);


			repository.save(personnel1);
			repository.save(new Personnel("Chloe", "O'Brian"));
			repository.save(new Personnel("Kim", "Bauer"));
			repository.save(new Personnel("David", "Palmer"));
			repository.save(new Personnel("Michelle", "Dessler"));

//			// fetch all customers
			log.info("Personnel found with findAll():");
			log.info("-------------------------------");
			for (Personnel personnel : repository.findAll()) {
				log.info(personnel.toString());
			}
			log.info("");
//
//			// fetch all customers
			log.info("Personnel findAllByFirstName() Yurii:");
			log.info("-------------------------------");
			for (Personnel personnel : repository.findAllByFirstName("Yurii")) {
				log.info(personnel.toString());
			}
			log.info("");

//			// fetch an individual customer by ID
//			Personnel personnel = repository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			//  log.info(bauer.toString());
//			// }
//			log.info("");
//		};

//	@Bean
//	public CommandLineRunner demo(SalaryRepository repository) {
//		return (args) -> {
//			// save a few customers
//			Personnel personnel1 = new Personnel("Yurii", "Liakhor");
//			Salary salary1 = new Salary(999999999, "UAH");
//			personnel1.setSalary(salary1);
//
//			salary1.setPersonnel(personnel1);
//
//
//			repository.save(salary1);
//
		};

	}
}
