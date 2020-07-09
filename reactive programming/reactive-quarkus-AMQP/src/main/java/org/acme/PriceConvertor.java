package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class PriceConvertor {

	@Incoming("prices")
	@Outgoing("my-data-stream")
	@Broadcast
	public double pricess(int priceInUsd) {
		System.out.println(priceInUsd);
		return priceInUsd * 0.88;
	}

}
