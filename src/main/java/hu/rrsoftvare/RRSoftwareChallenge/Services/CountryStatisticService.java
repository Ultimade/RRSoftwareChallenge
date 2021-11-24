package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;

import java.util.Date;

public interface CountryStatisticService {

    /**
     * create or refres daily statistic of a country
     * @param countryStatDto
     */
    public void createorUpdate(CountryStatDto countryStatDto);

    /**
     * return summ data of a country like all deaths all infected etc..
     * @param country
     * @return
     */
    public CountryStatDto getCountryStatistic(Countries country, Date day);


    /**
     * return summ data of a country like all deaths all infected etc..
     * @param country
     * @return
     */
    public CountryStatDto getCountryStatistic(CountryDto country);

    /**
     * return daily statisctic of a country
     * @param country
     * @param selectedDay
     * @return
     */
    public CountryStatDto getCountryStatisticByDate(CountryDto country, Date selectedDay);

    /**
     * return global summ data of deaths, infecteds etc..
     * @return
     */
    public CountryStatDto getGlobalStatisticList();

    /**
     * return global summ data of deaths, infecteds etc.. by region
     * @param region
     * @return
     */
    public CountryStatDto getRegionStatistic(String region);

}
