package hu.rrsoftvare.RRSoftwareChallenge.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "table_role")
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

}
