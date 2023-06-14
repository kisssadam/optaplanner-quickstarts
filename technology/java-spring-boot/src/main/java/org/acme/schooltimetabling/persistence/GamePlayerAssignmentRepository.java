package org.acme.schooltimetabling.persistence;

import org.acme.schooltimetabling.domain.GamePlayerAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GamePlayerAssignmentRepository extends CrudRepository<GamePlayerAssignment, Long>, PagingAndSortingRepository<GamePlayerAssignment, Long> {

    @Override
    List<GamePlayerAssignment> findAll();

}
