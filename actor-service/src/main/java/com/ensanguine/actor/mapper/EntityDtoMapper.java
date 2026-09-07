package com.ensanguine.actor.mapper;

import com.ensanguine.actor.dto.ActorDto;
import com.ensanguine.actor.entity.Actor;

public class EntityDtoMapper {

    public static ActorDto toDto(Actor actor){
        return new ActorDto(
                actor.getId(),
                actor.getName()
        );
    }

}
