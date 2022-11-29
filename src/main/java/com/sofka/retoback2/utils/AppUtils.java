package com.sofka.retoback2.utils;


import com.sofka.retoback2.dto.CountryDTO;
import com.sofka.retoback2.dto.CyclistDTO;
import com.sofka.retoback2.dto.CyclistTeamDTO;
import com.sofka.retoback2.models.Country;
import com.sofka.retoback2.models.Cyclist;
import com.sofka.retoback2.models.CyclistTeam;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Flux;

public class AppUtils {


    //-----------------MODEL TO DTO----------------------------------------------------------
    public static CountryDTO countryToDto(Country country){
        CountryDTO countryDTO = new CountryDTO();
        BeanUtils.copyProperties(country, countryDTO);
        return countryDTO;
    }
    public static CyclistDTO cyclistToDto(Cyclist cyclist){
        CyclistDTO cyclistDTO = new CyclistDTO();
        BeanUtils.copyProperties(cyclist, cyclistDTO);
        return cyclistDTO;
    }
    public static CyclistTeamDTO cyclistTeamToDto(CyclistTeam cyclistTeam){
        CyclistTeamDTO cyclistTeamDTO = new CyclistTeamDTO();
        BeanUtils.copyProperties(cyclistTeam, cyclistTeamDTO);
        return cyclistTeamDTO;
    }

    public static Flux<CountryDTO> countryListToDto(Flux<Country> countryFlux){
        Flux<CountryDTO> countryListDto = countryFlux.map(AppUtils::countryToDto);
        return countryListDto;
    }

    public static Flux<CyclistDTO> cyclistListToDto(Flux<Cyclist> cyclistFlux){
        Flux<CyclistDTO> cyclistListDto = cyclistFlux.map(AppUtils::cyclistToDto);
        return cyclistListDto;
    }

    public static Flux<CyclistTeamDTO> cyclistTeamListToDto(Flux<CyclistTeam> cyclistTeamFlux){
        Flux<CyclistTeamDTO> cyclistTeamDTO = cyclistTeamFlux.map(AppUtils::cyclistTeamToDto);
        return cyclistTeamDTO;
    }


    //--------DTO TO MODEL---------

    public static Country dtoToCountry(CountryDTO countryDTO){
        Country country = new Country();
        BeanUtils.copyProperties(countryDTO, country);
        return country;
    }
    public static Cyclist dtoToCyclist(CyclistDTO cyclistDto){
        Cyclist cyclist = new Cyclist();
        BeanUtils.copyProperties(cyclistDto, cyclist);
        return cyclist;
    }
    public static CyclistTeam dtoToCyclistTeam(CyclistTeamDTO cyclistTeamDTO){
        CyclistTeam cyclistTeam = new CyclistTeam();
        BeanUtils.copyProperties(cyclistTeamDTO, cyclistTeam);
        return cyclistTeam;
    }

}
