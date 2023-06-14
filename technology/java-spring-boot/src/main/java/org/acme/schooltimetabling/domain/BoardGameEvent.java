package org.acme.schooltimetabling.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;

@PlanningSolution
public class BoardGameEvent {

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    public List<Game> games;

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    public List<Player> players;

    @ProblemFactCollectionProperty
    public List<PlayerGroup> playerGroups;

    @PlanningEntityCollectionProperty
    public List<GamePlayerAssignment> gamePlayerAssignments;

    @PlanningScore
    private HardMediumSoftScore score;

    // Ignored by OptaPlanner, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;

    @SuppressWarnings("unused")
    public BoardGameEvent() {
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

}
