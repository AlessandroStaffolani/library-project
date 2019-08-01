package it.astaffolani.LibraryProject.rest;

import it.astaffolani.LibraryProject.controller.AuthorControllerLocal;
import it.astaffolani.LibraryProject.entity.AuthorEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/authors")
public class AuthorRest {

    @EJB
    private AuthorControllerLocal authorController;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAll() {
        List<AuthorEntity> authors = authorController.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("authors", authors);
        return result;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthorEntity add(AuthorEntity authorEntity) {
        authorController.insert(authorEntity);
        return authorEntity;
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AuthorEntity getById(@PathParam("id") long id) {
        return authorController.findById(id);
    }
}
