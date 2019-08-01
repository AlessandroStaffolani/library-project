package it.astaffolani.LibraryProject.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.astaffolani.LibraryProject.controller.ReservationControllerLocal;
import it.astaffolani.LibraryProject.entity.BookEntity;
import it.astaffolani.LibraryProject.entity.ReservationEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/reservations")
public class ReservationRest {

    @EJB
    private ReservationControllerLocal reservationController;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAll() {
        List<ReservationEntity> reservations = reservationController.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("reservations", reservations);
        return result;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReservationEntity addTest(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            try {
                return reservationController.insert(root);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
