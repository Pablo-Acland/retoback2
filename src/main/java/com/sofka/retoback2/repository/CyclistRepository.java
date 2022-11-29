package com.sofka.retoback2.repository;



import com.sofka.retoback2.models.Cyclist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface CyclistRepository extends ReactiveMongoRepository<Cyclist, String> {
	public Mono<Cyclist> findCyclistByCompetitorNumber(String competitorNumber);


}
