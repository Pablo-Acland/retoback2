package com.sofka.retoback2.interfaces;


import com.sofka.retoback2.dto.CountryDTO;
import com.sofka.retoback2.models.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ICountry {
    Flux<CountryDTO> findAllCountry();
    Mono<Country> save(Country country);
    Mono<Void> delete(String id);
    Optional<CountryDTO> findCountryByCode(String code);

}
