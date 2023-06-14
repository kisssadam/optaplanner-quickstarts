package org.acme.schooltimetabling.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class PlayerGroup {

    @Id
    @GeneratedValue
    public Long id;

    @OneToMany
    public List<Player> players;

    @SuppressWarnings("unused")
    public PlayerGroup() {
    }

}
