package hu.rrsoftvare.RRSoftwareChallenge.Models;


import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * osrszágok entitás
 */
@Entity
@Audited
@Table(name = "table_country")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Countries extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iso_code", unique = true)
    private String isoCode;

    @Column(name = "name")
    private String countryName;

    @Column(name = "region")
    private String region;

    @Column(name = "population")
    private Long population;



}
