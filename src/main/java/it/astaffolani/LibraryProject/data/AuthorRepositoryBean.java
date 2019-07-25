package it.astaffolani.LibraryProject.data;

import it.astaffolani.LibraryProject.entity.AuthorEntity;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorRepositoryBean {

    @PersistenceContext(unitName = "LibraryProjectPU")
    private EntityManager em;

    public AuthorRepositoryBean() {

    }

    public AuthorEntity insert(AuthorEntity author) {
        em.persist(author);
        return author;
    }

    public List<AuthorEntity> findAll() {
        return em.createQuery("SELECT a FROM AuthorEntity a").getResultList();
    }

    public AuthorEntity findById(long id) {
        return em.find(AuthorEntity.class, id);
    }

    public List<AuthorEntity> findByName(String name) {
        return em.createQuery("SELECT a FROM AuthorEntity a WHERE AuthorEntity.name LIKE :name")
                .setParameter("name", name)
                .getResultList();
    }

    public void remove(long id) {
        AuthorEntity author = this.findById(id);
        em.remove(author);
    }
}
