package com.example.springapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.app.controller","com.app.service","com.app.exception"})
@EnableJpaRepositories("com.app.repository")
@EntityScan("com.app.entity")
public class NewUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewUserServiceApplication.class, args);
		System.out.println("Welcome to Bus Booking App");
	}

}
