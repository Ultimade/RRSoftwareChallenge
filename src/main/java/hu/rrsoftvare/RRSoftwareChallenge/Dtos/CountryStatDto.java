package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CountryStatDto {

    private Long testing;

    private Long newInfected;

    private Long deaths;

    private Long healings;

    private CountryDto countryDto;

    private String region;
}
