package org.acme.schooltimetabling.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Player {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    @ManyToOne
    public Game preferredGame;

    @SuppressWarnings("unused")
    public Player() {
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id +
                ", name=" + name +
                ", preferredGame=" + preferredGame +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
