package com.codeoftheweb.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository) {
		return (args) -> {
			// save a couple of customers
			Player player1 = new Player("j.bauer@ctu.gov");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("kim_bauer@ctu.gov");
			Player player4 = new Player("t.almeida@ctu.gov");


			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			Date date1 = new Date(); //alt intro p Date importar clase
			Date date2 = Date.from(date1.toInstant().plusSeconds(3600)); //diferencia una hora
			Date date3 = Date.from(date2.toInstant().plusSeconds(3600));

			Game game1 = new Game (date1);
			Game game2 = new Game (date2);
			Game game3 = new Game (date3);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);


		};
	}
}

