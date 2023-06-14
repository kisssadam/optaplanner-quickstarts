package org.acme.schooltimetabling;

import org.acme.schooltimetabling.domain.Game;
import org.acme.schooltimetabling.domain.GamePlayerAssignment;
import org.acme.schooltimetabling.domain.Player;
import org.acme.schooltimetabling.domain.PlayerGroup;
import org.acme.schooltimetabling.persistence.GamePlayerAssignmentRepository;
import org.acme.schooltimetabling.persistence.GameRepository;
import org.acme.schooltimetabling.persistence.PlayerGroupRepository;
import org.acme.schooltimetabling.persistence.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BoardGameEventSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(BoardGameEventSpringBootApp.class, args);
    }

    @Bean
    public CommandLineRunner demoData(
            GameRepository gameRepository,
            PlayerRepository playerRepository,
            PlayerGroupRepository playerGroupRepository,
            GamePlayerAssignmentRepository gamePlayerAssignmentRepository) {
        return (args) -> {
            Game gameTerraformingMars = new Game();
            gameTerraformingMars.name = "Terraforming Mars";
            gameRepository.save(gameTerraformingMars);

            Game gameTicketToRide = new Game();
            gameTicketToRide.name = "Ticket To Ride";
            gameRepository.save(gameTicketToRide);

            Game gameCockRoachPoker = new Game();
            gameCockRoachPoker.name = "Cockroach Poker";
            gameRepository.save(gameCockRoachPoker);

            Player playerAdam = new Player();
            playerAdam.name = "Adam";
            playerAdam.preferredGame = gameTicketToRide;
            playerAdam = playerRepository.save(playerAdam);

            Player playerPeter = new Player();
            playerPeter.name = "Peter";
            playerPeter.preferredGame = gameTicketToRide;
            playerPeter = playerRepository.save(playerPeter);

            PlayerGroup playerGroup = new PlayerGroup();
            playerGroup.players = List.of(playerAdam, playerPeter);
            playerGroupRepository.save(playerGroup);

            GamePlayerAssignment gamePlayerAssignmentTerraformingMars = new GamePlayerAssignment();
            gamePlayerAssignmentTerraformingMars.game = gameTerraformingMars;
            gamePlayerAssignmentTerraformingMars.players = List.of();
            gamePlayerAssignmentRepository.save(gamePlayerAssignmentTerraformingMars);
        };
    }

}
