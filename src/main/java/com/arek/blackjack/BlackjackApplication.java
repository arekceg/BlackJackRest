package com.arek.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

}
