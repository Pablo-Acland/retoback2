package com.sofka.retoback2.repository;


import com.sofka.retoback2.dto.CountryDTO;
import com.sofka.retoback2.models.Country;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CountryRepository extends ReactiveMongoRepository<Country, String> {
	Optional<CountryDTO> findCountryByCode(String code);
}
