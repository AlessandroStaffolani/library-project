package it.astaffolani.LibraryProject.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Book
 *
 */
@Entity
@Table(name="book")
public class BookEntity implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	private String title;
	
	private int pages;
	
	@Column(name = "pubblication_date")
	private Date pubblicationDate;
	
	@ManyToMany(mappedBy = "books")
	private Set<AuthorEntity> authors = new HashSet<>();
	
	private String preface;
	
	private static final long serialVersionUID = 1L;
	
	public BookEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Date getPubblicationDate() {
		return pubblicationDate;
	}

	public void setPubblicationDate(Date pubblicationDate) {
		this.pubblicationDate = pubblicationDate;
	}

	public Set<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorEntity> authors) {
		this.authors = authors;
	}

	public String getPreface() {
		return preface;
	}

	public void setPreface(String preface) {
		this.preface = preface;
	}

}
