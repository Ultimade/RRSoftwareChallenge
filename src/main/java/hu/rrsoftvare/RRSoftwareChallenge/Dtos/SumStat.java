package hu.rrsoftvare.RRSoftwareChallenge.Dtos;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class SumStat {

    private Long deaths;

    private Long healing;

    private Long infected;

    private Long testing;

    public SumStat(Long deaths, Long healing, Long infected, Long testing){
        this.deaths = deaths;
        this.healing = healing;
        this.testing = testing;
        this.infected = infected;
    }
}
