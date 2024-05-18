package com.mycompany.sw2_eventcity.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
