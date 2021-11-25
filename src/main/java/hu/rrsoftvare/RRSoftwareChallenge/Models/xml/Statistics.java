package hu.rrsoftvare.RRSoftwareChallenge.Models.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "STATISTICS")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Statistics implements Serializable {

    @XmlElement(name = "STATISTIC")
    private List<Statistic> statistic;
}
