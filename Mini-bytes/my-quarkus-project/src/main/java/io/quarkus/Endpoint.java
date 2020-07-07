package io.quarkus;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.scheduler.Scheduled;

@Path("/token")
public class Endpoint {
    
    private String token;

    @Scheduled(every="10s")
    void generateToken() {
        token= UUID.randomUUID().toString();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken() {
        return token;
    }
}