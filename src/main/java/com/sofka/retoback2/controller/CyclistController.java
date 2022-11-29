package com.sofka.retoback2.controller;


import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.interfaces.ICyclist;
import com.sofka.retoback2.models.Cyclist;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cyclist")
public class CyclistController {
	@Autowired
	private ICyclist iCyclist;

	@GetMapping("/getAll")
	public ResponseEntity<Flux<CyclistDTO>> findAllCyclist() {
		return ResponseEntity.ok().body(iCyclist.findAllCyclists());
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<Flux<CyclistDTO>> findCyclistByCountry(@PathVariable(value = "country") String country) {
		return ResponseEntity.ok().body(iCyclist.findAllCyclistsByCountry(country));
	}

	@PostMapping("/crear")
	public ResponseEntity<Mono<Cyclist>> save(@RequestBody CyclistDTO cyclistDTO) {
		return ResponseEntity.ok().body(iCyclist.save(AppUtils.dtoToCyclist(cyclistDTO)));
	}
	
	@GetMapping("/competitorNumber/{competitorNumber}")
	public ResponseEntity<Mono<CyclistDTO>> findCyclistByCompetitorNumber(
			@PathVariable(name = "competitorNumber") String competitorNumber) {
		return ResponseEntity.ok().body(iCyclist.findCyclistByCompetitorNumber(competitorNumber));
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
		return iCyclist.delete(id)
				.then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
