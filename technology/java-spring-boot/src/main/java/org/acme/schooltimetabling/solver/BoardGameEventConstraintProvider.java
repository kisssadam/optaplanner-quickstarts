package org.acme.schooltimetabling.solver;

import org.acme.schooltimetabling.domain.GamePlayerAssignment;
import org.acme.schooltimetabling.domain.Player;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class BoardGameEventConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                // hardest constraint: host?
                actualGameIsPreferredGame(constraintFactory),
//                playerPlaysWithPreferredGroup(constraintFactory)


//                roomConflict(constraintFactory),
//                teacherConflict(constraintFactory),
//                studentGroupConflict(constraintFactory),
//                Soft constraints
//                teacherRoomStability(constraintFactory),
//                teacherTimeEfficiency(constraintFactory),
//                studentGroupSubjectVariety(constraintFactory)
        };
    }

    private Constraint actualGameIsPreferredGame(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(GamePlayerAssignment.class)
                .join(Player.class)
                // if player plays a non preferred game
                .filter((gamePlayerAssignment, player) -> !player.preferredGame.equals(gamePlayerAssignment.game))
                .penalize(HardMediumSoftScore.ONE_MEDIUM)
                .asConstraint("Test");

//        return constraintFactory
//                 If the player's assigned game does not match with his/her preferred game
//                .forEach(Player.class)
//                .filter(player -> !Objects.equals(player.getAssignedGame().getName(), player.getPreferredGame().getName()))
//                 then penalize it with a hard weight. TODO penalize with 3-(index of preferred game when there will be 3 preferred game).
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Assigned game is not preferred game.");
    }
//
//    private Constraint playerPlaysWithPreferredGroup(ConstraintFactory constraintFactory) {
//        // TODO how to penalize if a player is in a group, but not part of the preferred group
//        return constraintFactory
//                .forEach(Player.class)
//                .join(PlayerGroup.class, Joiners.filtering((player, playerGroup) -> !playerGroup.getPlayerList().contains(player)))
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Player is not playing with his/her preferred group.");
//    }

//
//    Constraint roomConflict(ConstraintFactory constraintFactory) {
//        // A room can accommodate at most one lesson at the same time.
//        return constraintFactory
//                // Select each pair of 2 different lessons ...
//                .forEachUniquePair(Lesson.class,
//                        // ... in the same timeslot ...
//                        Joiners.equal(Lesson::getTimeslot),
//                        // ... in the same room ...
//                        Joiners.equal(Lesson::getRoom))
//                // ... and penalize each pair with a hard weight.
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Room conflict");
//    }
//
//    Constraint teacherConflict(ConstraintFactory constraintFactory) {
//        // A teacher can teach at most one lesson at the same time.
//        return constraintFactory
//                .forEachUniquePair(Lesson.class,
//                        Joiners.equal(Lesson::getTimeslot),
//                        Joiners.equal(Lesson::getTeacher))
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Teacher conflict");
//    }
//
//    Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
//        // A student can attend at most one lesson at the same time.
//        return constraintFactory
//                .forEachUniquePair(Lesson.class,
//                        Joiners.equal(Lesson::getTimeslot),
//                        Joiners.equal(Lesson::getStudentGroup))
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Student group conflict");
//    }
//
//    Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
//        // A teacher prefers to teach in a single room.
//        return constraintFactory
//                .forEachUniquePair(Lesson.class,
//                        Joiners.equal(Lesson::getTeacher))
//                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
//                .penalize(HardSoftScore.ONE_SOFT)
//                .asConstraint("Teacher room stability");
//    }
//
//    Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
//        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
//        return constraintFactory
//                .forEach(Lesson.class)
//                .join(Lesson.class, Joiners.equal(Lesson::getTeacher),
//                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
//                .filter((lesson1, lesson2) -> {
//                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
//                            lesson2.getTimeslot().getStartTime());
//                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
//                })
//                .reward(HardSoftScore.ONE_SOFT)
//                .asConstraint("Teacher time efficiency");
//    }
//
//    Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
//        // A student group dislikes sequential lessons on the same subject.
//        return constraintFactory
//                .forEach(Lesson.class)
//                .join(Lesson.class,
//                        Joiners.equal(Lesson::getSubject),
//                        Joiners.equal(Lesson::getStudentGroup),
//                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
//                .filter((lesson1, lesson2) -> {
//                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
//                            lesson2.getTimeslot().getStartTime());
//                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
//                })
//                .penalize(HardSoftScore.ONE_SOFT)
//                .asConstraint("Student group subject variety");
//    }

}
