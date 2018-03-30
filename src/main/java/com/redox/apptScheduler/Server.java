package com.redox.apptScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Server entry point, starts up the embedded server application
 */
@SpringBootApplication
@EnableJpaRepositories
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}
}
