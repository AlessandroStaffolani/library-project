package it.astaffolani.LibraryProject.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

	private String description;

	@Column(name = "birth_date")
	private Date birthDate;

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "author_book", 
        joinColumns = { @JoinColumn(name = "author_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
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

}
