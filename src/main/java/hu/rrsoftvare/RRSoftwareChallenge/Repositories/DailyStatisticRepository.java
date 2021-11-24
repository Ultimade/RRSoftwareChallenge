package hu.rrsoftvare.RRSoftwareChallenge.Repositories;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.SumStat;
import hu.rrsoftvare.RRSoftwareChallenge.Models.Countries;
import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DailyStatisticRepository extends JpaRepository<DailyStatistic, Long> {
    DailyStatistic findByCountryAndDayBetween(Countries country, Date dayStart, Date dayEnd);

    @Query( nativeQuery = true)
    SumStat getSummDataByCountry_Named(Long id);
}