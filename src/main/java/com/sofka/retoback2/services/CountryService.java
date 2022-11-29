package com.sofka.retoback2.services;

import com.sofka.retoback2.dto.CountryDTO;
import com.sofka.retoback2.interfaces.ICountry;
import com.sofka.retoback2.models.Country;
import com.sofka.retoback2.repository.CountryRepository;
import com.sofka.retoback2.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CountryService implements ICountry {

    @Autowired
    private CountryRepository repository;

    @Override
    public Flux<CountryDTO> findAllCountry() {
        return AppUtils.countryListToDto(repository.findAll().switchIfEmpty(Flux.empty()));
    }

    @Override
    public Mono<Country> save(Country country) {
        return repository.save(country);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository
                .findById(id)
                .flatMap(country -> repository.deleteById(country.getId()))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Optional<CountryDTO> findCountryByCode(String code){
        return repository.findCountryByCode(code);
    }

}
