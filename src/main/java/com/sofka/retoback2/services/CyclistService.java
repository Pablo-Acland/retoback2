package com.sofka.retoback2.services;


import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.interfaces.ICyclist;
import com.sofka.retoback2.models.Cyclist;
import com.sofka.retoback2.repository.CyclistRepository;
import com.sofka.retoback2.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CyclistService implements ICyclist {

    @Autowired
    private CyclistRepository repository;

    @Override
    public Flux<CyclistDTO> findAllCyclists() {
        return AppUtils.cyclistListToDto(repository.findAll().switchIfEmpty(Flux.empty()));
    }

    @Override
    public Mono<Cyclist> save(Cyclist cyclist) {
        return repository.save(cyclist);
    }

    @Override
    public Flux<CyclistDTO> findAllCyclistsByCountry(String country) {
        return AppUtils.cyclistListToDto(repository.findAll()
                .filter(cyclist -> cyclist.getCountry().getName().equals(country))
                .switchIfEmpty(Flux.empty()));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(cyclist -> repository.deleteById(cyclist.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistDTO> findCyclistByCompetitorNumber(String competitorNumber) {
        return repository.findCyclistByCompetitorNumber(competitorNumber)
                .map(AppUtils::cyclistToDto)
                .switchIfEmpty(Mono.empty());
    }

}
