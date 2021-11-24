package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;

import java.util.List;

public interface CountryService {

    public CountryDto getCountryDtoDatasByNameOrIso(CountryDto countryDto);
    public List<CountryDto> getCountryList();
    public List<CountryDto> getCountryList(String region);

}
