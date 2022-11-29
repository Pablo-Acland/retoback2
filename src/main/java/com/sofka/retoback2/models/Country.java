package com.sofka.retoback2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

	@Id
	private String id;

	private String name;

	private String code;

	private List<CyclistTeam> cyclingTeams;

	private List<Cyclist> cyclists;
}
