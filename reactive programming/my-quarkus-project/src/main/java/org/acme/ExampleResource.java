package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/hello")
public class ExampleResource {

	@Inject
	ReactiveGreetingService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/greeting/{name}")
	public Uni<String> greeting(@PathParam(value="name") String name) {
		return service.greeting(name);
	}
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@Path("/greeting/{count}/{name}")
	public Multi<String> greetings(@PathParam(value="count") int count, @PathParam(value="name") String name) {
	  return service.greetings(count, name);
	}
	
	
	
}