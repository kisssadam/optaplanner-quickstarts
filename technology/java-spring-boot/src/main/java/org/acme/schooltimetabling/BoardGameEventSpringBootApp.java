package org.acme.schooltimetabling;

import org.acme.schooltimetabling.domain.Game;
import org.acme.schooltimetabling.domain.Player;
import org.acme.schooltimetabling.persistence.GameRepository;
import org.acme.schooltimetabling.persistence.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoardGameEventSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(BoardGameEventSpringBootApp.class, args);
    }

    @Bean
    public CommandLineRunner demoData(
            GameRepository gameRepository,
            PlayerRepository playerRepository) {
        return (args) -> {
            Game terraformingMars = new Game("Terraforming Mars");
            Game ticketToRide = new Game("Ticket To Ride");
            Game cockRoachPoker = new Game("Cockroach Poker");
            gameRepository.save(terraformingMars);
            gameRepository.save(ticketToRide);
            gameRepository.save(cockRoachPoker);

            playerRepository.save(new Player("Adam", terraformingMars, ticketToRide));
            playerRepository.save(new Player("Peter", terraformingMars, cockRoachPoker));
        };
    }

}
