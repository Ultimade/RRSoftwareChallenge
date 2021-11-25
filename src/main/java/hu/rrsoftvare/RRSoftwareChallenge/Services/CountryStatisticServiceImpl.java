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
import java.util.Date;
import java.util.List;

@Service
public class CountryStatisticServiceImpl implements CountryStatisticService {

    @Autowired
    CountryServiceImpl countryService;

    @Autowired
    DailyStatisticRepository dailyStatisticRepository;

    /**
     * a kapott statisztikai adatokat entity formára hozza és menti az adatbázisba
     */
    @Override
    public void createOrUpdate(CountryStatDto countryStatDto) {
        createOrUpdateStatistic(mapDtoToEntity(countryStatDto));
    }
    /**
     * a kapott statisztikai adatokat ha léteznek felül írja, ha nem akkor betölti az adatbázisba
     */
    public void createOrUpdateStatistic(DailyStatistic dailyStat){
        Countries country = countryService.getCountryDatasByNameOrIso(dailyStat.getCountry().getIsoCode(), dailyStat.getCountry().getCountryName());
        if (country != null) {
            DailyStatistic dailyStatistic = getDailyStatisticByDate(country, dailyStat.getDay());
            if (dailyStatistic == null) {
                dailyStatistic = new DailyStatistic();
                dailyStatistic.setCountry(country);
                dailyStatistic.setDay(dailyStat.getDay());
                dailyStatistic.setCreatedBy("pasz");
                dailyStatistic.setCreatedDate(new Date());
            }

            dailyStatistic.setTesting(dailyStat.getTesting());
            dailyStatistic.setHealing(dailyStat.getHealing());
            dailyStatistic.setDeaths(dailyStat.getDeaths());
            dailyStatistic.setNewInfected(dailyStat.getNewInfected());
            dailyStatistic.setModifiedBy("pasz2");
            dailyStatistic.setUpdatedDate(new Date());

            try {
                dailyStatisticRepository.save(dailyStatistic);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * visszaadja egy adott ország összesített statisztikáját
     */
    @Override
    public CountryStatDto getCountryStatistic(CountryDto countryDto) {

        SumStat dailyStatisticObj = dailyStatisticRepository.getSummDataByCountry_Named(
                countryService.getCountryDatasByNameOrIso(countryDto).getId());
        return createCountryStatDto(dailyStatisticObj, null, countryService.getCountryDatasByNameOrIso(countryDto));
    }

    /**
     * a az adott dátumot a nap kezdetére állítja
     */
    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    /**
     * a az adott dátumot a nap végére állítja
     */
    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    /**
     * Date obijektumból LocalDateTime-t készít
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime obijektumból Date-t készít
     */
    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Dátum alapján megkeresi az adott ország statisztikáját
     */
    @Override
    public CountryStatDto getCountryStatisticByDate(CountryDto countryDto) {
        return getCountryStatisticByDate(countryService.getCountryDatasByNameOrIso(countryDto), countryDto.getSelectedDay());
    }

    /**
     * ország entitás és dátum alapján visszaadja a statisztikát. ha nem adunk meg dátumot akkor az adott napi statisztikát adja vissza
     */
    public CountryStatDto getCountryStatisticByDate(Countries country, Date day) {
        return mapEntityToDto(getDailyStatisticByDate(country, day == null ? new Date(): day));
    }

    /**
     * ország entitás és egy időintervallum között (nap kezdete-nap vége) visszaadja a statisztikát
     */
    public DailyStatistic getDailyStatisticByDate(Countries country, Date day){
        Date dayStart = atStartOfDay(day);
        Date dayEnd = atEndOfDay(day);
        return dailyStatisticRepository.findByCountryAndDayBetween(country, dayStart, dayEnd);
    }

    /**
     * visszaadja a globális statisztikát
     */
    @Override
    public CountryStatDto getGlobalStatistic() {
        SumStat dailyStatisticObj = dailyStatisticRepository.getSummData_Named();
        return createCountryStatDto(dailyStatisticObj, null, null);
    }

    /**
     * visszaadja az egy régióra vonatkozó statisztikát
     */
    @Override
    public CountryStatDto getRegionStatistic(String region) {
        SumStat dailyStatisticObj = dailyStatisticRepository.getSummDataByRegion_Named(region);
        return createCountryStatDto(dailyStatisticObj, region, null);

    }

    /**
     * modelből csinál dto-t
     */
    private CountryStatDto mapEntityToDto (DailyStatistic dailyStatistic){
        return new CountryStatDto(dailyStatistic.getTesting(), dailyStatistic.getNewInfected(),
                dailyStatistic.getDeaths(), dailyStatistic.getHealing(),
                countryService.mapEntityToDto(dailyStatistic.getCountry()), dailyStatistic.getCountry().getRegion(),
                dailyStatistic.getDay());

    }

    /**
     * dtoból csinál model-t
     */
    private DailyStatistic mapDtoToEntity (CountryStatDto countryStatDto){
        return new DailyStatistic(null, countryStatDto.getTesting(), countryStatDto.getNewInfected(),
                countryStatDto.getDeaths(), countryStatDto.getHealings(),countryStatDto.getDay(),
                countryService.mapDtoToEntity(countryStatDto.getCountryDto()));

    }

    /**
     * visszaadja az összes statisztikai adatot
     */
    public List<DailyStatistic> getDailyStatisticList(){
        return dailyStatisticRepository.findAll();
    }

    /**
     * összerakja a statisztika válasz dto-át
     */
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
