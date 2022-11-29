package com.sofka.retoback2.controller;



import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.dto.CyclistTeamDTO;
import com.sofka.retoback2.interfaces.ICyclistTeam;
import com.sofka.retoback2.models.CyclistTeam;
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
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/team")
public class CyclingTeamController {

	@Autowired
	private ICyclistTeam iCyclistTeam;

	@GetMapping("/getAll")
	public ResponseEntity<Flux<CyclistTeamDTO>> findAllCyclistTeam() {
		return ResponseEntity.ok().body(iCyclistTeam.findAllCyclingTeams());
	}

	@GetMapping("/{team_code}")
	public ResponseEntity<Mono<CyclistTeamDTO>> findCyclingTeamByCode(@PathVariable(value = "team_code") String teamCode) {
		return ResponseEntity.ok().body(iCyclistTeam.findCyclingTeamByTeamCode(teamCode));
	}

	@GetMapping("/cyclist/{teamCode}")
	public ResponseEntity<Flux<CyclistDTO>> findAllCyclingByTeamCode(@PathVariable("teamCode") String teamCode) {
		return ResponseEntity.ok().body(iCyclistTeam.findAllCyclingByTeamCode(teamCode));
	}

	@PutMapping("/addCyclistToTeam/{id}/{idCyclist}")
	public Mono<CyclistTeamDTO> addCyclistToTeam(@PathVariable("id") String id, @PathVariable("idCyclist") String idCyclist) {
		return iCyclistTeam.addCyclistToTeamByTeamIDAndIdCylist(id, idCyclist);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){
		return iCyclistTeam.delete(id)
				.then(Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping("/crear/{id}")
	public ResponseEntity<Mono<CyclistTeam>> save(@RequestBody CyclistTeamDTO cyclistTeamDTO, @PathVariable("id") String id) throws ExecutionException, InterruptedException {
		return ResponseEntity.ok().body(iCyclistTeam.saveCyclingTeam(AppUtils.dtoToCyclistTeam(cyclistTeamDTO), id));
	}

}
