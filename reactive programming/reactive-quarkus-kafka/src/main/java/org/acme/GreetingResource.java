package org.acme;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;



@Path("/hello")
public class GreetingResource {

	@Inject
	EventBus eventBus;

	@Path("/{name}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> hello(@PathParam(value="name")String name) {
		
		return eventBus
				.<String>request("greeting", name)
				.onItem()
				.apply(Message::body);
		
		
		
	}
}