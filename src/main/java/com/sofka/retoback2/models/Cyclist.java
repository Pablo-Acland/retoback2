package com.sofka.retoback2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist {
    @Id
    private String id;

    private String fullName;

    private String competitorNumber;

    private Country country;

    private CyclistTeam cyclingTeam;
}
