package it.astaffolani.LibraryProject.data;

import it.astaffolani.LibraryProject.entity.BookEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookRepositoryBean {

    @PersistenceContext(unitName = "LibraryProjectPU")
    private EntityManager em;

    public BookRepositoryBean() {

    }

    public BookEntity insert(BookEntity book) {
        em.persist(book);
        return book;
    }

    public BookEntity update(BookEntity book) {
        if (em.contains(book)) {
            // Managed state
            em.persist(book);
        } else {
            // Detached state
            em.merge(book);
        }
        return book;
    }

    public List<BookEntity> findAll() {
        return em.createQuery("SELECT b FROM BookEntity b").getResultList();
    }

    public BookEntity findById(long id) {
        return em.find(BookEntity.class, id);
    }

    public List<BookEntity> findByTitle(String title) {
        return em.createQuery("SELECT b FROM BookEntity b WHERE BookEntity.title LIKE :title")
                .setParameter("title", title)
                .getResultList();
    }

    public void remove(long id) {
        BookEntity book = this.findById(id);
        em.remove(book);
    }
}
