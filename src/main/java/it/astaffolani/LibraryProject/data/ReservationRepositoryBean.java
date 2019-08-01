package it.astaffolani.LibraryProject.data;

import it.astaffolani.LibraryProject.entity.ReservationEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReservationRepositoryBean {

    @PersistenceContext(unitName = "LibraryProjectPU")
    private EntityManager em;

    public ReservationRepositoryBean() {

    }

    public ReservationEntity insert(ReservationEntity reservation) {
        em.persist(reservation);
        return reservation;
    }

    public List<ReservationEntity> findAll() {
        return em.createQuery("SELECT r FROM ReservationEntity r").getResultList();
    }

    public ReservationEntity findById(long id) {
        return em.find(ReservationEntity.class, id);
    }

    public void remove(long id) {
        ReservationEntity reservation = this.findById(id);
        em.remove(reservation);
    }
}
