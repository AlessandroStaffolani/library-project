package it.astaffolani.LibraryProject.rest;

import it.astaffolani.LibraryProject.controller.UserControllerLocal;
import it.astaffolani.LibraryProject.entity.UserEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserRest {

    @EJB
    private UserControllerLocal userController;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAll() {
        List<UserEntity> users = userController.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("users", users);
        return result;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity add(UserEntity user) {
        userController.insert(user);
        return user;
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getById(@PathParam("id") long id) {
        return userController.findById(id);
    }

    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getByUsername(@PathParam("username") String username) {
        return userController.findByUsername(username);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity getByEmail(@PathParam("email") String email) {
        return userController.findByEmail(email);
    }
}
