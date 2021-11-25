package hu.rrsoftvare.RRSoftwareChallenge.Controllers;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryStatDto;
import hu.rrsoftvare.RRSoftwareChallenge.Models.xml.Statistics;
import hu.rrsoftvare.RRSoftwareChallenge.Services.CountryStatisticServiceImpl;
import hu.rrsoftvare.RRSoftwareChallenge.Services.XmlServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


/**
 * covid statisztikai adatokkal kapcsolatos api végpontok
 */
@RestController
@RequestMapping(path = "/statistic", name = "Statistic")
public class CountryStatisticRestController {

    private final CountryStatisticServiceImpl countryStatisticService;
    private final XmlServiceImpl xmlService;

    public CountryStatisticRestController(CountryStatisticServiceImpl countryStatisticService, XmlServiceImpl xmlService) {
        this.countryStatisticService = countryStatisticService;
        this.xmlService = xmlService;
    }

    /**
     * create or ubdate statistic
     */
    @PostMapping(value = "/api/addOrEitStatistic")
    public void addOrEitStatistic(@RequestBody CountryStatDto countryStatDto){
        String asd = "asd";
        countryStatisticService.createOrUpdate(countryStatDto);
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

    /**
     * xml formában visszaadja a statisztikai adatokat
     */
    @GetMapping(value ="/api/getStatisticXml", produces = { MediaType.APPLICATION_XML_VALUE  })
    public Statistics getStatisticXml(){
        return xmlService.createXmlFromDb();
    }

    @GetMapping(value ="/api/uploadStatisticFromXml")
    public void  uploadStatisticFromXml(@RequestBody Statistics statistics){
        xmlService.convertXmlToEntityList(statistics);
    }

}
