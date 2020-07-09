package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

@Path("/price")
public class PriceResource {

	@Inject
	@Channel("my-data-stream")
	Publisher<Double> prices;

	@GET
	@Path("/stream")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Publisher<Double> stream() {
		return prices;
	}

}
