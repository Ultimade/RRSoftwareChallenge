package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CountryStatisticServiceImpl implements CountryStatisticService {
    @Override
    public void createorUpdate(CountryStatDto countryStatDto) {

    }

    @Override
    public CountryStatDto getCountryStatistic(CountryDto country) {
        return null;
    }

    @Override
    public CountryStatDto getCountryStatisticByDate(CountryDto country, Date selectedDay) {
        return null;
    }

    @Override
    public CountryStatDto getGlobalStatisticList() {
        return null;
    }

    @Override
    public CountryStatDto getRegionStatistic(String region) {
        return null;
    }
}
