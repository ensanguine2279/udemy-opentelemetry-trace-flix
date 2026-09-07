package com.ensanguine.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensanguine.actor.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
