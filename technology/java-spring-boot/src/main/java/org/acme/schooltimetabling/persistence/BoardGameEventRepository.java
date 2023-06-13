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

    public BoardGameEvent findById(Long id) {
        if (!SINGLETON_TIME_TABLE_ID.equals(id)) {
            throw new IllegalStateException("There is no boardGameEvent with id (" + id + ").");
        }
        // Occurs in a single transaction, so each initialized lesson references the same timeslot/room instance
        // that is contained by the timeTable's timeslotList/roomList.
        return new BoardGameEvent(
                gameRepository.findAll(),
                playerRepository.findAll());
    }

    public void save(BoardGameEvent boardGameEvent) {
        boardGameEvent.getPlayerList().forEach(player -> {
//            // TODO this is awfully naive: optimistic locking causes issues if called by the SolverManager
            playerRepository.findById(player.getId()).ifPresent(p -> {
                p.setAssignedGame(player.getAssignedGame());
                p.setPreferredGame(player.getPreferredGame());
            });
        });
//        for (Lesson lesson : timeTable.getLessonList()) {
//            lessonRepository.findById(lesson.getId()).ifPresent(attachedLesson -> {
//                attachedLesson.setTimeslot(lesson.getTimeslot());
//                attachedLesson.setRoom(lesson.getRoom());
//            });
//        }
    }

}
