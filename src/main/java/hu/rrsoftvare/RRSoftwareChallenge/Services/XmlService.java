package hu.rrsoftvare.RRSoftwareChallenge.Services;

import hu.rrsoftvare.RRSoftwareChallenge.Models.DailyStatistic;
import hu.rrsoftvare.RRSoftwareChallenge.Models.xml.Statistics;

import java.util.List;

public interface XmlService {

    public Statistics createXmlFromDb();

    public void convertXmlToEntityList(Statistics statistic);
}
