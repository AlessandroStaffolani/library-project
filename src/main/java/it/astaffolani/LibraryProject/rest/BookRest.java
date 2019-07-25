package it.astaffolani.LibraryProject.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.astaffolani.LibraryProject.controller.BookControllerLocal;
import it.astaffolani.LibraryProject.entity.BookEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Path("/books")
public class BookRest {

    @EJB
    private BookControllerLocal bookController;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAll() {
        List<BookEntity> books = bookController.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("books", books);
        return result;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BookEntity addTest(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            return bookController.insert(root);
        } else {
            return null;
        }
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BookEntity getById(@PathParam("id") long id) {
        return bookController.findById(id);
    }
}
