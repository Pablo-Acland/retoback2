package com.sofka.retoback2.services;


import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.dto.CyclistTeamDTO;
import com.sofka.retoback2.interfaces.ICyclistTeam;
import com.sofka.retoback2.models.CyclistTeam;
import com.sofka.retoback2.repository.CountryRepository;
import com.sofka.retoback2.repository.CyclingTeamRepository;
import com.sofka.retoback2.repository.CyclistRepository;
import com.sofka.retoback2.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
public class CyclistTeamService implements ICyclistTeam {

    @Autowired
    private CyclingTeamRepository repository;

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Flux<CyclistTeamDTO> findAllCyclingTeams() {
        return AppUtils.cyclistTeamListToDto(repository.findAll());
    }

    @Override
    public Flux<CyclistDTO> findAllCyclingByTeamCode(String teamCode) {
        return AppUtils.cyclistListToDto(repository.findAll()
                .filter(cyclistTeam -> cyclistTeam.getTeamCode().equals(teamCode))
                .flatMapIterable(CyclistTeam::getCyclists));
    }

    @Override
    public Mono<CyclistTeam> saveCyclingTeam(CyclistTeam cyclistTeam, String idCountry) throws ExecutionException, InterruptedException {
        cyclistTeam.setCountry(countryRepository.findById(idCountry).toFuture().get());

        return repository.save(cyclistTeam);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(cyclistTeam -> repository.deleteById(cyclistTeam.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<CyclistTeamDTO> findCyclingTeamByTeamCode(String teamCode) {
        return repository.findCyclingTeamByTeamCode(teamCode).map(AppUtils::cyclistTeamToDto);
    }

    @Override
    public Mono<CyclistTeamDTO> addCyclistToTeamByTeamIDAndIdCylist(String idTeam, String idCyclist) {
        return repository.findById(idTeam)
                .flatMap(cyclistTeam -> {
                    if (cyclistTeam.getCyclists().size() < 8) {
                        return cyclistRepository.findById(idCyclist)
                                .flatMap(cyclist -> {
                                    cyclistTeam.getCyclists().add(cyclist);
                                    return repository.save(cyclistTeam);
                                });
                    }
                    return Mono.just(cyclistTeam);
                }).map(AppUtils::cyclistTeamToDto);
    }

}
