package com.codeoftheweb.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Player("mailuno@gmail.com"));
			repository.save(new Player("maildos@gmail.com"));
			repository.save(new Player("mailtres@gmail.com"));
			repository.save(new Player("mailcuatro@gmail.com"));
			repository.save(new Player("mailcinco@gmail.com"));
		};
	}
}

