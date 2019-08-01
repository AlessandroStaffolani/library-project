package it.astaffolani.LibraryProject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity
@Table(name="reservation")
public class ReservationEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "created_at", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties("reservations")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name="book_id", nullable=false)
    private BookEntity book;

    private static final long serialVersionUID = 1L;

    public ReservationEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
