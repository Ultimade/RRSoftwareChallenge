package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.*;

import java.util.Date;

/**
 * api végpontokhoz használt statisztika osztály
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatDto {

    private Long testing;

    private Long newInfected;

    private Long deaths;

    private Long healings;

    private CountryDto countryDto;

    private String region;

    private Date day;
}
