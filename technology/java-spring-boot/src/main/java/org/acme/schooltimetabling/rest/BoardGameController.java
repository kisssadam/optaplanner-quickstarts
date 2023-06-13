package org.acme.schooltimetabling.rest;

import org.acme.schooltimetabling.domain.BoardGameEvent;
import org.acme.schooltimetabling.persistence.BoardGameEventRepository;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolutionManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardGameEvent")
public class BoardGameController {

    @Autowired
    private BoardGameEventRepository boardGameEventRepository;
    @Autowired
    private SolverManager<BoardGameEvent, Long> solverManager;
    @Autowired
    private SolutionManager<BoardGameEvent, HardSoftScore> solutionManager;

    // To try, GET http://localhost:8080/boardGameEvent
    @GetMapping()
    public BoardGameEvent getBoardGameEvent() {
        // Get the solver status before loading the solution
        // to avoid the race condition that the solver terminates between them
        SolverStatus solverStatus = getSolverStatus();
        BoardGameEvent solution = boardGameEventRepository.findById(BoardGameEventRepository.SINGLETON_TIME_TABLE_ID);
        solutionManager.update(solution); // Sets the score
        solution.setSolverStatus(solverStatus);
        return solution;
    }

    @PostMapping("/solve")
    public void solve() {
        solverManager.solveAndListen(BoardGameEventRepository.SINGLETON_TIME_TABLE_ID,
                boardGameEventRepository::findById,
                boardGameEventRepository::save);
    }

    public SolverStatus getSolverStatus() {
        return solverManager.getSolverStatus(BoardGameEventRepository.SINGLETON_TIME_TABLE_ID);
    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(BoardGameEventRepository.SINGLETON_TIME_TABLE_ID);
    }

}
