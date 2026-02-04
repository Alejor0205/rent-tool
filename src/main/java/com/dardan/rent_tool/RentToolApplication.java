package com.dardan.rent_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RentToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentToolApplication.class, args);
	}

}
