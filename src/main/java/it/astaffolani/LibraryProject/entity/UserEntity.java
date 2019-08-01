package it.astaffolani.LibraryProject.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @Column(unique = true)
    @NotEmpty
    private String email;

    private String telephone;

    @OneToMany(cascade=ALL, mappedBy="user", fetch = FetchType.EAGER)
    private Set<ReservationEntity> reservations;

    private static final long serialVersionUID = 1L;

    public UserEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationEntity> reservations) {
        this.reservations = reservations;
    }
}
