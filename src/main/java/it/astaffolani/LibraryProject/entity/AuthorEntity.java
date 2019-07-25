package it.astaffolani.LibraryProject.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Author
 *
 */
@Entity
@Table(name="author")
public class AuthorEntity implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;

	@NotEmpty
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "birth_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthDate;

	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "authors", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("authors")
	private Set<BookEntity> books = new HashSet<>();

	private static final long serialVersionUID = 1L;

	public AuthorEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Set<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(Set<BookEntity> books) {
		this.books = books;
	}

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", birthDate=" + birthDate +
                ", books=" + books +
                '}';
    }
}
