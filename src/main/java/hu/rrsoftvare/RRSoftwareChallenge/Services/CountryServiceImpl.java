package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountriesRepository countriesRepository;

    @Override
    public CountryDto getCountryDatasByNameOrIso(CountryDto countryDto) {
        return mapEntityToDto(countriesRepository.findByIsoCodeOrCountryName(countryDto.getIsoCode(), countryDto.getIsoCode()!= null ? null : countryDto.getCountryName()));
    }

    @Override
    public List<CountryDto> getCountryList() {
        return null;
    }

    @Override
    public List<CountryDto> getCountryList(String region) {
        return null;
    }

    private CountryDto mapEntityToDto(Countries countries){
        return new CountryDto(countries.getIsoCode(), countries.getCountryName(), countries.getRegion(), countries.getPopulation());
    }
}
