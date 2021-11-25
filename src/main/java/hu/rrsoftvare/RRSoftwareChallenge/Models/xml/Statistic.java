package hu.rrsoftvare.RRSoftwareChallenge.Models.xml;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "STATISTIC")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Statistic implements Serializable {

    @XmlAttribute(name = "StatId")
    private Long id;

    @XmlElement(name = "Tested")
    private Long testing;

    @XmlElement(name = "Infected")
    private Long newInfected;

    @XmlElement(name = "Dead")
    private Long deaths;

    @XmlElement(name = "Healed")
    private Long healings;

    @XmlElement(name = "Country")
    private Country country;

    @XmlElement(name = "RiportDay")
    private Date day;
}
