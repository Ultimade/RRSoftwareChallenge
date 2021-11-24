package hu.rrsoftvare.RRSoftwareChallenge.Models;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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
    private Long healings;

    @ManyToOne
    @JoinColumn(name = "country")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Countries country;

}
