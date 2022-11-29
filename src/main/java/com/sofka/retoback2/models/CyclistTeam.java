package com.sofka.retoback2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistTeam {

	@Id
	private String id;

	private String name;

	private String teamCode;

	private Country country;

	private List<Cyclist> cyclists;
}
