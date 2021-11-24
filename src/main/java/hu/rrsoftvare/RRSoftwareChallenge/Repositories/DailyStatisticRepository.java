package hu.rrsoftvare.RRSoftwareChallenge.Repositories;

import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStatisticRepository extends JpaRepository<DailyStatistic, Long> {
}