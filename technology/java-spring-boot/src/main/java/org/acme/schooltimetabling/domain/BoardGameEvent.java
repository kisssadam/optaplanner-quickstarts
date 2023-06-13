package org.acme.schooltimetabling.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;

@PlanningSolution
public class BoardGameEvent {

    @ValueRangeProvider
    private List<Game> gameList;

    @PlanningEntityCollectionProperty
    private List<Player> playerList;

    @PlanningScore
    private HardSoftScore score;

    // Ignored by OptaPlanner, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;

    // No-arg constructor required for OptaPlanner
    public BoardGameEvent() {
    }

    public BoardGameEvent(List<Game> gameList, List<Player> playerList) {
        this.gameList = gameList;
        this.playerList = playerList;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************


    public List<Game> getGameList() {
        return gameList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

}
