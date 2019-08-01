package it.astaffolani.LibraryProject.data;

import it.astaffolani.LibraryProject.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepositoryBean {

    @PersistenceContext(unitName = "LibraryProjectPU")
    private EntityManager em;

    public UserRepositoryBean() {

    }

    public UserEntity insert(UserEntity user) {
        em.persist(user);
        return user;
    }

    public List<UserEntity> findAll() {
        return em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    public UserEntity findById(long id) {
        return em.find(UserEntity.class, id);
    }

    public UserEntity findByUsername(String username) {
        List<UserEntity> results = em.createQuery("SELECT u FROM UserEntity u WHERE UserEntity.username LIKE :username")
                .setParameter("username", username)
                .getResultList();
        if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public UserEntity findByEmail(String email) {
        List<UserEntity> results = em.createQuery("SELECT u FROM UserEntity u WHERE UserEntity.email = :email")
                .setParameter("email", email)
                .getResultList();

        if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public void remove(long id) {
        UserEntity user = this.findById(id);
        em.remove(user);
    }
}
