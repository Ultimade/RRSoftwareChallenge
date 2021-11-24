package hu.rrsoftvare.RRSoftwareChallenge.Controllers;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.CountryDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/country", name = "Country")
public class CountryRestController {

    /**
     * Retudn country datas  if country is exist, return empty object if country not found
     * @param countryDto
     * @return
     */
    @GetMapping(value = "/api/getCountry")
        public CountryDto getCountry(@RequestBody CountryDto countryDto){
        return new CountryDto();
        }
}
