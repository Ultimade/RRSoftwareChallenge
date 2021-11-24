package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.SumStat;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import hu.rrsoftvare.RRSoftwareChallenge.Repositories.DailyStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CountryStatisticServiceImpl implements CountryStatisticService {

    @Autowired
    CountryServiceImpl countryService;

    @Autowired
    DailyStatisticRepository dailyStatisticRepository;

    @Override
    public void createOrUpdate(CountryStatDto countryStatDto) {
        Countries country = countryService.getCountryDatasByNameOrIso(countryStatDto.getCountryDto());
        if (country != null) {
            DailyStatistic dailyStatistic = getDailyStatisticByDate(country, countryStatDto.getDay());
            if (dailyStatistic == null) {
                dailyStatistic = new DailyStatistic();
                dailyStatistic.setCountry(country);
                dailyStatistic.setDay(countryStatDto.getDay());
                dailyStatistic.setCreatedBy("pasz");
                dailyStatistic.setCreatedDate(new Date());
            }

            dailyStatistic.setTesting(countryStatDto.getTesting());
            dailyStatistic.setHealing(countryStatDto.getHealings());
            dailyStatistic.setDeaths(countryStatDto.getDeaths());
            dailyStatistic.setNewInfected(countryStatDto.getNewInfected());
            dailyStatistic.setModifiedBy("pasz2");
            dailyStatistic.setUpdatedDate(new Date());
            
            try {
                dailyStatisticRepository.save(dailyStatistic);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public CountryStatDto getCountryStatistic(CountryDto countryDto) {

        SumStat dailyStatisticObj = dailyStatisticRepository.getSummDataByCountry_Named(
                countryService.getCountryDatasByNameOrIso(countryDto).getId());
        return createCountryStatDto(dailyStatisticObj, null, countryService.getCountryDatasByNameOrIso(countryDto));
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
    public CountryStatDto getCountryStatisticByDate(CountryDto countryDto) {
        return getCountryStatisticByDate(countryService.getCountryDatasByNameOrIso(countryDto), countryDto.getSelectedDay());
    }
    
    public CountryStatDto getCountryStatisticByDate(Countries country, Date day) {
        return mapEntityToDto(getDailyStatisticByDate(country, day == null ? new Date(): day));
    }
    public DailyStatistic getDailyStatisticByDate(Countries country, Date day){
        Date dayStart = atStartOfDay(day);
        Date dayEnd = atEndOfDay(day);
        return dailyStatisticRepository.findByCountryAndDayBetween(country, dayStart, dayEnd);
    }

    @Override
    public CountryStatDto getGlobalStatistic() {
        SumStat dailyStatisticObj = dailyStatisticRepository.getSummData_Named();
        return createCountryStatDto(dailyStatisticObj, null, null);
    }

    @Override
    public CountryStatDto getRegionStatistic(String region) {
        SumStat dailyStatisticObj = dailyStatisticRepository.getSummDataByRegion_Named(region);
        return createCountryStatDto(dailyStatisticObj, region, null);

    }

    private CountryStatDto mapEntityToDto (DailyStatistic dailyStatistic){
        return new CountryStatDto(dailyStatistic.getTesting(), dailyStatistic.getNewInfected(),
                dailyStatistic.getDeaths(), dailyStatistic.getHealing(),
                countryService.mapEntityToDto(dailyStatistic.getCountry()), dailyStatistic.getCountry().getRegion(),
                dailyStatistic.getDay());

    }

    private CountryStatDto createCountryStatDto(SumStat stat, String region, Countries country){
        CountryStatDto dailyStatistic = new CountryStatDto();
        dailyStatistic.setRegion(region);
        dailyStatistic.setDeaths(stat.getDeaths());
        dailyStatistic.setHealings(stat.getHealing());
        dailyStatistic.setTesting(stat.getTesting());
        dailyStatistic.setNewInfected(stat.getInfected());
        dailyStatistic.setCountryDto(country != null ? countryService.mapEntityToDto(country) : null);
        return dailyStatistic;
    }
}
