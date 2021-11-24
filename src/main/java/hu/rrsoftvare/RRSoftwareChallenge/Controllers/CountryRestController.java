package hu.rrsoftvare.RRSoftwareChallenge.Controllers;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import hu.rrsoftvare.RRSoftwareChallenge.Services.CountryServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/country", name = "Country")
public class CountryRestController {

    private final CountryServiceImpl countryService;

    public CountryRestController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    /**
     * Retudn country datas  if country is exist, return empty object if country not found
     * @param countryDto
     * @return
     */
    @GetMapping(value = "/api/getCountry")
    public CountryDto getCountry(@RequestBody CountryDto countryDto){
        return countryService.getCountryDtoDatasByNameOrIso(countryDto);
    }
}
