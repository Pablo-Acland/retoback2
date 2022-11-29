package com.sofka.retoback2.interfaces;

import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.models.Cyclist;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICyclist {
    Flux<CyclistDTO> findAllCyclists();
    Mono<Cyclist> save(Cyclist cyclist);
    Flux<CyclistDTO> findAllCyclistsByCountry(String country);
    Mono<Void> delete(String id);
    Mono<CyclistDTO> findCyclistByCompetitorNumber(String competitorNumber);

}
