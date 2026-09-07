package com.ensanguine.actor.service;

import org.springframework.stereotype.Service;

import com.ensanguine.actor.dto.ActorDto;
import com.ensanguine.actor.mapper.EntityDtoMapper;
import com.ensanguine.actor.repository.ActorRepository;

import java.util.Optional;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Optional<ActorDto> getActor(Integer id) {
        this.simulateProcessingTime(id);
        return this.actorRepository.findById(id)
                                   .map(EntityDtoMapper::toDto);
    }

    // simulate slow processing
    private void simulateProcessingTime(Integer id){
        if(id <= 14)
            return;
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
