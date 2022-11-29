package com.sofka.retoback2.interfaces;


import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.dto.CyclistTeamDTO;
import com.sofka.retoback2.models.CyclistTeam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface ICyclistTeam {
    Flux<CyclistTeamDTO> findAllCyclingTeams();
    Mono<CyclistTeam> saveCyclingTeam(CyclistTeam cyclistTeam, String idCountry) throws ExecutionException, InterruptedException;
    Flux<CyclistDTO> findAllCyclingByTeamCode(String teamCode);
    Mono<Void> delete(String id);
    Mono<CyclistTeamDTO> findCyclingTeamByTeamCode(String teamCode);
    Mono<CyclistTeamDTO> addCyclistToTeamByTeamIDAndIdCylist(String idTeam, String idCyclist);
}
