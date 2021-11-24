package hu.rrsoftvare.RRSoftwareChallenge.Repositories;

import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DailyStatisticRepository extends JpaRepository<DailyStatistic, Long> {
    DailyStatistic findByCountryAndDayBetween(Countries country, Date dayStart, Date dayEnd);
}