package hu.rrsoftvare.RRSoftwareChallenge.Models;

import hu.rrsoftvare.RRSoftwareChallenge.Dtos.SumStat;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQuery(name = "DailyStatistic.getSummDataByCountry_Named",
        query = "SELECT SUM(ds.deaths) as deaths,   SUM(ds.healing) as healing, SUM(ds.new_infected) as infected, " +
                "SUM(ds.testing) as testing from table_daily_stat ds WHERE ds.country = :id group by ds.country",
        resultSetMapping = "Mapping.SumStat")
@NamedNativeQuery(name = "DailyStatistic.getSummDataByRegion_Named",
        query = "SELECT SUM(ds.deaths) as deaths, SUM(ds.healing) as healing, SUM(ds.new_infected) as infected, " +
                "SUM(ds.testing) as testing from table_daily_stat ds " +
                "inner join table_country tc on tc.id = ds.country where tc.region =:region group by tc.region",
        resultSetMapping = "Mapping.SumStat")
@SqlResultSetMapping(name = "Mapping.SumStat",
        classes = @ConstructorResult(targetClass = SumStat.class,
                columns = {@ColumnResult(name = "deaths", type=Long.class),
                        @ColumnResult(name = "healing", type=Long.class),
                        @ColumnResult(name = "infected", type=Long.class),
                        @ColumnResult(name = "testing", type=Long.class)}))
@Entity
@Audited
@Table(name = "table_daily_stat")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DailyStatistic  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "testing")
    private Long testing;

    @Column(name = "new_infected")
    private Long newInfected;

    @Column(name = "deaths")
    private Long deaths;

    @Column(name = "healing")
    private Long healing;

    @Column(name = "day")
    private Date day;

    @ManyToOne
    @JoinColumn(name = "country")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Countries country;

}
