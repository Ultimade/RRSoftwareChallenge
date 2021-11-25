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

    /**
     * iso kód vagy országnév lapján megkeres egy országot és azt dto-ban visszaadja
     */
    @Override
    public CountryDto getCountryDtoDatasByNameOrIso(CountryDto countryDto) {
        return mapEntityToDto(countriesRepository.findByIsoCodeOrCountryName(countryDto.getIsoCode(), countryDto.getIsoCode()!= null ? null : countryDto.getCountryName()));
    }

    /**
     * iso kód vagy országnév lapján megkeres egy országot és azt modellként visszaadja
     */
    public Countries getCountryDatasByNameOrIso(CountryDto countryDto) {
        return getCountryDatasByNameOrIso(countryDto.getIsoCode() , countryDto.getCountryName());
    }

    /**
     * iso kód vagy országnév lapján megkeres egy országot és azt modellként visszaadja
     */
    public Countries getCountryDatasByNameOrIso(String isoCode, String countryName) {
        return countriesRepository.findByIsoCodeOrCountryName(isoCode, isoCode!= null ? null : countryName);
    }

    /**
     * dto listában visszadja az országokat
     */
    @Override
    public List<CountryDto> getCountryList() {
        return convertEntityListToDtoList(countriesRepository.findAll());
    }

    /**
     * egy régióba tartozó országok adatait adja vissza dto listában
     */
    @Override
    public List<CountryDto> getCountryList(String region) {
        return convertEntityListToDtoList(countriesRepository.findByRegion(region));
    }

    /**
     * a modell-ből dto-t mappol
     */
    public CountryDto mapEntityToDto(Countries countries){
        return new CountryDto(countries.getIsoCode(), countries.getCountryName(), countries.getRegion(), countries.getPopulation(), null);
    }

    /**
     * a dto-ból model-t mappol
     */
    public Countries mapDtoToEntity(CountryDto countryDto){
        return new Countries(null, countryDto.getIsoCode(), countryDto.getCountryName(), countryDto.getRegion(), countryDto.getPopulation());
    }

    /**
     * modell listából dto listát készít
     */
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
