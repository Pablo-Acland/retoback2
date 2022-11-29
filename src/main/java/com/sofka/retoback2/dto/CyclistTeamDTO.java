package com.sofka.retoback2.dto;



import com.sofka.retoback2.models.Country;
import com.sofka.retoback2.models.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistTeamDTO {

    private String id;

    private String name;

    private String teamCode;

    private Country country;

    private List<Cyclist> cyclists;
}
