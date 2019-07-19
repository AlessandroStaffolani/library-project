package it.astaffolani.LibraryProject.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/authors")
public class AuthorRest {
	
	@GET
	@Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> sayHello(@PathParam("name") String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "Hello " + name);
        return result;
    }

}
