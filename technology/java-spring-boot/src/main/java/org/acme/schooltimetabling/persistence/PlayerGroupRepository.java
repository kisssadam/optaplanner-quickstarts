package org.acme.schooltimetabling.persistence;

import org.acme.schooltimetabling.domain.PlayerGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerGroupRepository extends CrudRepository<PlayerGroup, Long>, PagingAndSortingRepository<PlayerGroup, Long> {

    @Override
    List<PlayerGroup> findAll();

}