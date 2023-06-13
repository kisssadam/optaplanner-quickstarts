package org.acme.schooltimetabling.persistence;

import org.acme.schooltimetabling.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long>, PagingAndSortingRepository<Player, Long> {

    @Override
    List<Player> findAll();

}
