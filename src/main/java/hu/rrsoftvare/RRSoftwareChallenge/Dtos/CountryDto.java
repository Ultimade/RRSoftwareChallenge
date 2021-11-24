package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CountryDto {

    private String isoCode;

    private String countryName;

    private String region;

    private Long population;
}
