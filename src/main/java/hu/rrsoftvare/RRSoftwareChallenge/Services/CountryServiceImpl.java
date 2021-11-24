package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountriesRepository countriesRepository;

    @Override
    public CountryDto getCountryDtoDatasByNameOrIso(CountryDto countryDto) {
        return mapEntityToDto(countriesRepository.findByIsoCodeOrCountryName(countryDto.getIsoCode(), countryDto.getIsoCode()!= null ? null : countryDto.getCountryName()));
    }

    public Countries getCountryDatasByNameOrIso(CountryDto countryDto) {
        return countriesRepository.findByIsoCodeOrCountryName(countryDto.getIsoCode(), countryDto.getIsoCode()!= null ? null : countryDto.getCountryName());
    }

    @Override
    public List<CountryDto> getCountryList() {
        return convertEntityListToDtoList(countriesRepository.findAll());
    }

    @Override
    public List<CountryDto> getCountryList(String region) {
        return convertEntityListToDtoList(countriesRepository.findByRegion(region));
    }

    public CountryDto mapEntityToDto(Countries countries){
        return new CountryDto(countries.getIsoCode(), countries.getCountryName(), countries.getRegion(), countries.getPopulation(), null);
    }

    public List<CountryDto> convertEntityListToDtoList(List<Countries> countries){

        List<CountryDto> countryList = new ArrayList<>();
        for (Countries c: countries
        ) {
            CountryDto countryDto = mapEntityToDto(c);
            countryList.add(countryDto);
        }
        return countryList;
    }
}
