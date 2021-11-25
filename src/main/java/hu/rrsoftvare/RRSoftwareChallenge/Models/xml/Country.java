package hu.rrsoftvare.RRSoftwareChallenge.Models.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "COUNTRY")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Country implements Serializable {

    @XmlAttribute(name = "CountryId")
    private Long id;

    @XmlElement(name = "ISOCode")
    private String isoCode;

    @XmlElement(name = "CountryName")
    private String countryName;

    @XmlElement(name = "Region")
    private String region;

    @XmlElement(name = "Population")
    private Long population;
}
