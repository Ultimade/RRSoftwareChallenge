package hu.rrsoftvare.RRSoftwareChallenge.Controllers;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import hu.rrsoftvare.RRSoftwareChallenge.Services.CountryStatisticServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/statistic", name = "Statistic")
public class CountryStatisticRestController {

    private final CountryStatisticServiceImpl countryStatisticService;

    public CountryStatisticRestController(CountryStatisticServiceImpl countryStatisticService) {
        this.countryStatisticService = countryStatisticService;
    }

    /**
     * create or ubdate statistic
     */
    @PostMapping(value = "/api/addOrEitStatistic")
    public void addOrEitStatistic(@RequestBody CountryStatDto countryStatDto){
    }


    /**
     * Retudn country statistic  if country is exist, return empty object if country not found
     */
    @GetMapping(value = "/api/getCountryStatisticByDate")
    public CountryStatDto getCountryStatisticByDate(@RequestBody CountryDto countryDto){
        return countryStatisticService.getCountryStatisticByDate(countryDto);
    }


    /**
     * return summ statistic by country
     */
    @GetMapping(value = "/api/getCountryMassStatistic")
    public CountryStatDto getCountryMassStatistic(@RequestBody CountryDto countryDto){
        return countryStatisticService.getCountryStatistic(countryDto);
    }


    /**
     * return summ statistic by region
     */
    @GetMapping(value = "/api/getRegionMassStatistic")
    public CountryStatDto getRegionMassStatistic(@RequestBody String region){
        return countryStatisticService.getRegionStatistic(region);
    }


    /**
     * return summ statistic by region
     */
    @GetMapping(value = "/api/getMassStatistic")
    public CountryStatDto getMassStatistic(){
        return countryStatisticService.getGlobalStatistic();
    }

}
