package com.sofka.retoback2.dto;


import com.sofka.retoback2.models.Country;
import com.sofka.retoback2.models.CyclistTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {

    private String id;

    private String fullName;

    private String competitorNumber;

    private Country country;

    private CyclistTeam cyclingTeam;
}
