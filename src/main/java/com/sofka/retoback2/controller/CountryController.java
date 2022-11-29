package com.sofka.retoback2.controller;

import com.sofka.retoback2.dto.CountryDTO;
import com.sofka.retoback2.interfaces.ICountry;
import com.sofka.retoback2.models.Country;
import com.sofka.retoback2.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private ICountry icountry;
	
	@GetMapping("/getAll")
	public Flux<CountryDTO> findAllCountries() {
		return icountry.findAllCountry();
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<CountryDTO> findCountryByCode(@PathVariable(name = "code") String code) {
		Optional<CountryDTO> country = icountry.findCountryByCode(code);
		if (country.isPresent()) {
			return ResponseEntity.ok().body(country.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/create")
	public Mono<Country> saveCountry(@Validated @RequestBody CountryDTO countryDTO) {
		return icountry.save(AppUtils.dtoToCountry(countryDTO));
	}
	
	@PutMapping("/country/{id}")
	public Mono<Country> updateCountry(@RequestBody CountryDTO newCountry, @PathVariable String id) {
		return icountry.findCountryByCode(id).map(country -> {
			country.setName(newCountry.getName());
			country.setCode(newCountry.getCode());
			return icountry.save(AppUtils.dtoToCountry(country));
		})
		.orElseGet(() -> {
			return icountry.save(AppUtils.dtoToCountry(newCountry));
		});
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
		return icountry.delete(id)
				.then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
