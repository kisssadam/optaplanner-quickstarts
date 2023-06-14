package org.acme.schooltimetabling.domain;


import jakarta.persistence.*;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningListVariable;

import java.util.List;

@PlanningEntity
@Entity
public class GamePlayerAssignment {

    @PlanningId
    @Id
    @GeneratedValue
    public Long id;

    @OneToOne
    public Game game;

    @PlanningListVariable
    @OneToMany(fetch = FetchType.EAGER)
    public List<Player> players;

    @SuppressWarnings("unused")
    public GamePlayerAssignment() {
    }

    @Override
    public String toString() {
        return "GamePlayerAssignment{" +
                "game=" + game +
                ", players=" + players +
                '}';
    }

}
