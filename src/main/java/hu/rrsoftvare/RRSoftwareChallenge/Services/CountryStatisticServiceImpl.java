package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import hu.rrsoftvare.RRSoftwareChallenge.Repositories.DailyStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CountryStatisticServiceImpl implements CountryStatisticService {

    @Autowired
    CountryServiceImpl countryService;

    @Autowired
    DailyStatisticRepository dailyStatisticRepository;

    @Override
    public void createorUpdate(CountryStatDto countryStatDto) {

    }

    @Override
    public CountryStatDto getCountryStatistic(CountryDto countryDto) {
        return getCountryStatistic(countryService.getCountryDatasByNameOrIso(countryDto), countryDto.getSelectedDay());
    }

    public CountryStatDto getCountryStatistic(Countries country, Date day) {
        Date dayStart = atStartOfDay(day);
        Date dayEnd = atEndOfDay(day);
        return mapEntityToDto(dailyStatisticRepository.findByCountryAndDayBetween(country, dayStart, dayEnd));
    }
    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }
    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
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

    private CountryStatDto mapEntityToDto (DailyStatistic dailyStatistic){
        return new CountryStatDto(dailyStatistic.getTesting(), dailyStatistic.getNewInfected(),
                dailyStatistic.getDeaths(), dailyStatistic.getHealings(),
                countryService.mapEntityToDto(dailyStatistic.getCountry()), dailyStatistic.getCountry().getRegion(),
                dailyStatistic.getDay());

    }
}
