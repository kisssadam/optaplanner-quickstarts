package org.acme.schooltimetabling.persistence;

import org.acme.schooltimetabling.domain.BoardGameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardGameEventRepository {

    // There is only one tableGameAssignment, so there is only tableGameAssignmentId (= problemId).
    public static final Long SINGLETON_TIME_TABLE_ID = 1L;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerGroupRepository playerGroupRepository;

    @Autowired
    private GamePlayerAssignmentRepository gamePlayerAssignmentRepository;

    public BoardGameEvent findById(Long id) {
        if (!SINGLETON_TIME_TABLE_ID.equals(id)) {
            throw new IllegalStateException("There is no boardGameEvent with id (" + id + ").");
        }
        // Occurs in a single transaction, so each initialized lesson references the same timeslot/room instance
        // that is contained by the timeTable's timeslotList/roomList.
        BoardGameEvent boardGameEvent = new BoardGameEvent();
        boardGameEvent.games = gameRepository.findAll();
        boardGameEvent.players = playerRepository.findAll();
        boardGameEvent.playerGroups = playerGroupRepository.findAll();
        boardGameEvent.gamePlayerAssignments = gamePlayerAssignmentRepository.findAll();
        return boardGameEvent;
    }

    public void save(BoardGameEvent boardGameEvent) {
        boardGameEvent.gamePlayerAssignments.forEach(gamePlayerAssignment -> {
            gamePlayerAssignmentRepository.findById(gamePlayerAssignment.id).ifPresent(gpa -> {
                gpa.players = gamePlayerAssignment.players;
            });
        });
    }

}
