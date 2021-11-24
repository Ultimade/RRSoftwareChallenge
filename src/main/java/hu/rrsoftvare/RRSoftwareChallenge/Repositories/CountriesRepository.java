package hu.rrsoftvare.RRSoftwareChallenge.Repositories;

import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Countries, Long> {

    Countries findByIsoCodeOrCountryName(String isoCode, String name);
}