package com.example.company;

import com.example.company.entity.*;
import com.example.company.repository.CarRepository;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.JobRepository;
import com.example.company.repository.WorkerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
					new Worker("David", "Gilmour", "FR815369"),
					Worker.builder().firstName("Юрій").lastName("Ляхор").passport("EE148822").salary(new Salary(3, "USD")).build(),
					new Worker("Vitaliy", "Kim", "KK123532"),
					new Worker("David", "Palmer", "HI012353"),
					new Worker("Патрон", "Пес", "KY212432"),
					new Worker("Патрон", "Пес", "Клон", "KY2124322")
			);

			workerList.get(4).setBirthDate(new GregorianCalendar(1880, Calendar.FEBRUARY, 11).getTime());

			repository.saveAll(workerList);
		};
	}

	@Bean
	public CommandLineRunner initSalary(WorkerRepository repository) {
		return (args) -> {
			log.info("initSalary");

			Worker worker1 = repository.findWorkerByPassport("FR815369").orElseGet(() -> Worker.builder().build());
			Worker worker2 = repository.findWorkerByPassport("KK123532").orElseGet(() -> Worker.builder().build());
			Worker worker3 = repository.findWorkerByPassport("HI012353").orElseGet(() -> Worker.builder().build());
			Worker worker4 = repository.findWorkerByPassport("KY212432").orElseGet(() -> Worker.builder().build());

			worker1.setSalary(new Salary(999999, "GBP"));
			worker2.setSalary(new Salary(999999999, "UAH"));
			worker3.setSalary(new Salary(999999999, "HYI"));
			worker4.setSalary(new Salary(999999999, "UAH"));

			repository.saveAll(Arrays.asList(worker1, worker2, worker3, worker4));
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

	@Bean
	public CommandLineRunner initCars(CarRepository carRepository, WorkerRepository workerRepository, CompanyRepository companyRepository) {
		return (args) -> {
			log.info("initCars");

			Worker worker1 = workerRepository.findFirstByFirstName("Юрій");
			List<Car> carList = Arrays.asList(
					new Car("Tesla", "X", "AA1234BB"),
					new Car("Tesla", "Y", "AI2482HH")
			);
			carList.get(0).setWorker(worker1);
			carList.get(1).setWorker(worker1);

			Company company1 = companyRepository.findFirstByCompanyName("SkyService");
			carList.get(0).setCompany(company1);
			carList.get(1).setCompany(company1);

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
}