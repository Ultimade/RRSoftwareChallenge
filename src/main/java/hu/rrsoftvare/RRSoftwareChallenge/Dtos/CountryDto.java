package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.*;

import java.util.Date;

/**
 * api komunikációhoz használt ország osztály
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    private String isoCode;

    private String countryName;

    private String region;

    private Long population;

    private Date selectedDay;
}
