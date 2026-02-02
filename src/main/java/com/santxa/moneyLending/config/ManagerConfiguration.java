package com.santxa.moneyLending.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.santxa.moneyLending.repository.ManagerRepository;
import com.santxa.moneyLending.models.Manager;

@Configuration
public class ManagerConfiguration {
    
	@Bean
	CommandLineRunner loadMangers(ManagerRepository managerRepsitory) {
		return args ->{
			if(managerRepsitory.findByUsername("admin").isEmpty()) {
				Manager manager = new Manager("santha","admin","admin123");
				managerRepsitory.save(manager);
			}
		};
	}
}
