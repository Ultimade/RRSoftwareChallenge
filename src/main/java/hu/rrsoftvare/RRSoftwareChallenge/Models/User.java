package hu.rrsoftvare.RRSoftwareChallenge.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * autentikációhoz és audithoz szükséges felhasználó entitás
 */
@Entity
@Table(name = "table_user")
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an Email")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Please provide your password")
    @org.springframework.data.annotation.Transient
    private String password;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Please provide your username")
    private String username;

    @Column(name = "fullname")
    @NotEmpty(message = "Please provide your real name")
    private String fullname;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @NotAudited
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public User() {
    }

    public User( String email, String password, String username, boolean active, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.active = active;
        this.roles = roles;
    }

    public boolean hasRole(String role) {
        for (Role item : this.roles) {
            if (item.getRole().equals(role)) {
                return true;
            }

        }
        return false;
    }

    public void removeRoles() {
        this.roles.clear();
    }


}
