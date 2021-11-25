package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import hu.rrsoftvare.RRSoftwareChallenge.Models.xml.Country;
import hu.rrsoftvare.RRSoftwareChallenge.Models.xml.Statistic;
import hu.rrsoftvare.RRSoftwareChallenge.Models.xml.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class XmlServiceImpl implements XmlService {

    @Autowired
    CountryStatisticServiceImpl countryStatisticService;

    /**
     * xml obijektumot készít az adatbázisban tárolt adatokból
     */
    @Override
    public Statistics createXmlFromDb() {
        List<DailyStatistic> list = countryStatisticService.getDailyStatisticList();

        Statistics statistics = new Statistics();
        statistics.setStatistic(convertStatisticEntityListToXmlList(list));
        return statistics;
    }

    /**
     * xml állományból adstokat tölt az adatbázisba
     */
    @Override
    public void convertXmlToEntityList(Statistics statistics) {
        List<DailyStatistic> dailyStatisticList = convertXmlListToStatisticEntityList(statistics.getStatistic());
        for (DailyStatistic d :dailyStatisticList
             ) {
            countryStatisticService.createOrUpdateStatistic(d);
        }
    }

    /**
     * ország entity-ből mappol xml-t
     */
    private Country mapCountryEntityToXml(Countries countries){
        Country country = new Country();
        country.setId(countries.getId());
        country.setCountryName(countries.getCountryName());
        country.setIsoCode(countries.getIsoCode());
        country.setPopulation(countries.getPopulation());
        country.setRegion(countries.getRegion());
        return country;
    }

    /**
     * xml-ből mapol country entity-t
     */
    private Countries mapXmlToCountryEntity(Country country){
        Countries countries = new Countries();
        countries.setId(country.getId());
        countries.setCountryName(country.getCountryName());
        countries.setIsoCode(country.getIsoCode());
        countries.setPopulation(country.getPopulation());
        countries.setRegion(country.getRegion());
        return countries;
    }

    /**
     * DailyStatistic entity-ből mapol xml-t
     */
    private Statistic mapStatisticEntityToXml(DailyStatistic dailyStatistic){
        Statistic statistic = new Statistic();
        statistic.setId(dailyStatistic.getId());
        statistic.setTesting(dailyStatistic.getTesting());
        statistic.setDeaths(dailyStatistic.getDeaths());
        statistic.setHealings(dailyStatistic.getHealing());
        statistic.setNewInfected(dailyStatistic.getNewInfected());
        statistic.setDay(dailyStatistic.getDay());
        statistic.setCountry(mapCountryEntityToXml(dailyStatistic.getCountry()));
        return statistic;
    }

    /**
     * xml-ből mapol DailyStatistic entity-t
     */
    private DailyStatistic mapXmTolStatisticEntity(Statistic statistic){
        DailyStatistic dailyStatistic = new DailyStatistic();
        dailyStatistic.setId(statistic.getId());
        dailyStatistic.setTesting(statistic.getTesting());
        dailyStatistic.setDeaths(statistic.getDeaths());
        dailyStatistic.setHealing(statistic.getHealings());
        dailyStatistic.setNewInfected(statistic.getNewInfected());
        dailyStatistic.setDay(statistic.getDay());
        dailyStatistic.setCountry(mapXmlToCountryEntity(statistic.getCountry()));
        return dailyStatistic;
    }

    /**
     * entity listából konvertál xml listába
     */
    private List<Statistic> convertStatisticEntityListToXmlList(List<DailyStatistic> dailyStatisticList){
        List<Statistic> statisticList = new ArrayList<>();
        for (DailyStatistic daily : dailyStatisticList
        ) {
            statisticList.add(mapStatisticEntityToXml(daily));
        }
        return statisticList;
    }

    /**
     * xml listából csinál entity listát
     */
    private List<DailyStatistic> convertXmlListToStatisticEntityList(List<Statistic> statisticList){
        List<DailyStatistic> dailyStatisticList = new ArrayList<>();
        for (Statistic statistic : statisticList
        ) {
            dailyStatisticList.add(mapXmTolStatisticEntity(statistic));
        }
        return dailyStatisticList;
    }
}
