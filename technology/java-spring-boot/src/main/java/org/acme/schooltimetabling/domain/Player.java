package org.acme.schooltimetabling.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
@Entity
public class Player {

    @PlanningId
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @PlanningVariable
    @ManyToOne
    private Game assignedGame;

    @ManyToOne
    private Game preferredGame;

    // No-arg constructor required for Hibernate and OptaPlanner
    public Player() {
    }

    public Player(String name, Game assignedGame, Game preferredGame) {
        this.name = name;
        this.assignedGame = assignedGame;
        this.preferredGame = preferredGame;
    }

    public Player(Long id, String name, Game assignedGame, Game preferredGame) {
        this.id = id;
        this.name = name;
        this.assignedGame = assignedGame;
        this.preferredGame = preferredGame;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id +
                ", name=" + name +
                ", assignedGame=" + assignedGame +
                ", preferredGame=" + preferredGame +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Game getAssignedGame() {
        return assignedGame;
    }

    public void setAssignedGame(Game assignedGame) {
        this.assignedGame = assignedGame;
    }

    public Game getPreferredGame() {
        return preferredGame;
    }

    public void setPreferredGame(Game preferredGame) {
        this.preferredGame = preferredGame;
    }

}
