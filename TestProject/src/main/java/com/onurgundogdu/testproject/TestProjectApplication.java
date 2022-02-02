package com.onurgundogdu.testproject;

import com.onurgundogdu.testproject.entity.Customer;
import com.onurgundogdu.testproject.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestProjectApplication {

    private static final Logger log=LoggerFactory.getLogger(TestProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {

            repository.save(new Customer("Onur", "Gündoğdu",28));
            repository.save(new Customer("Chloe", "O'Brian",35));
            repository.save(new Customer("Kim", "Bauer",37));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            log.info("Customer found with findByLastName('Gündoğdu'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Gündoğdu").forEach(gundogdu -> {
                log.info(gundogdu.toString());
            });
            log.info("");

            log.info("Customer found with findTopByFirstNameIsStartingWith('C'):");
            log.info("--------------------------------------------");
            repository.findTopByFirstNameIsStartingWith("C").forEach(first -> {
                log.info(first.toString());
            });
            log.info("");

            log.info("Customer age found equals 28");
            log.info("--------------------------------------------");
            repository.findByAgeEquals(28).forEach(age->{
                log.info(age.toString());
            });
            log.info("Customer age found greater and equals 35");
            log.info("--------------------------------------------");
            repository.findByAgeGreaterThanEqual(35).forEach(age->{
                log.info(age.toString());
            });
            repository.findByAgeBetween(27,36).forEach(between->{
                log.info(between.toString());
            });

        };
    }
}
