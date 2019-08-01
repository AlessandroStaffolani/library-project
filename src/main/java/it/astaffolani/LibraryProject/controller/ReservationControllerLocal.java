package it.astaffolani.LibraryProject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import it.astaffolani.LibraryProject.entity.ReservationEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ReservationControllerLocal {

    public ReservationEntity insert(ReservationEntity reservation);
    public ReservationEntity insert(JsonNode jsonReservation) throws Exception;
    public List<ReservationEntity> findAll();
    public ReservationEntity findById(long id);
}
