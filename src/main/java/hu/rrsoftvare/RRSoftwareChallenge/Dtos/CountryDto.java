package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.*;


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
}
