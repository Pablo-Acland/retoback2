package com.sofka.retoback2.repository;


import com.sofka.retoback2.models.CyclistTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface CyclingTeamRepository extends ReactiveMongoRepository<CyclistTeam, String> {
	public Mono<CyclistTeam> findCyclingTeamByTeamCode(String teamCode);
}
