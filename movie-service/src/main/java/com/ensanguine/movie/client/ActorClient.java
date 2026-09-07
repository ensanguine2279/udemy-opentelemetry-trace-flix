package com.ensanguine.movie.client;

import org.springframework.web.client.RestClient;

import com.ensanguine.movie.dto.ActorDto;

public class ActorClient {

    private final RestClient restClient;

    public ActorClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ActorDto getActor(Integer actorId) {
        return this.restClient.get()
                              .uri("/{actorId}", actorId)
                              .retrieve()
                              .body(ActorDto.class);
    }

}
