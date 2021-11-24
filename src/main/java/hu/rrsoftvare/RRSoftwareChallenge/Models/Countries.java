package hu.rrsoftvare.RRSoftwareChallenge.Models;


import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "table_country")
@Data
public class Countries extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "iso_code", unique = true)
    private String isoCode;

    @Column(name = "name")
    private String countryName;

    @Column(name = "region", unique = true)
    private String region;

    @Column(name = "population", unique = true)
    private Long population;



}