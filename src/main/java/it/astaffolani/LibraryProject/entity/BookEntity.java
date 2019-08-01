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
import com.fasterxml.jackson.databind.JsonNode;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date pubblicationDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("books")
	private Set<AuthorEntity> authors = new HashSet<>();

	@Column(columnDefinition = "TEXT")
	private String preface;

	@Column(name = "n_copies", columnDefinition="INT default '10'")
	private int nCopies;

	private static final long serialVersionUID = 1L;
	
	public BookEntity() {
		super();
	}

	public BookEntity(JsonNode jsonBook) {

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

    public int getnCopies() {
        return nCopies;
    }

    public void setnCopies(int nCopies) {
        this.nCopies = nCopies;
    }

    @Override
	public String toString() {
		return "BookEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", pages=" + pages +
				", pubblicationDate=" + pubblicationDate +
				", authors=" + authors +
				", preface='" + preface + '\'' +
				'}';
	}
}
