package org.acme;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class GreetingService {

//	@ConsumeEvent(value = "greeting", blocking = false)
//	public String consume(String name) {
//		return "hello " + name.toUpperCase();
//	}

//	@ConsumeEvent(value = "greeting", blocking = false)
//	public CompletionStage<String> consume(String name) {
//		return CompletableFuture.supplyAsync(() -> {
//			return "hello " + name.toUpperCase();
//		});
//	}

	

	@ConsumeEvent(value = "greeting", blocking = false)
	public Uni<String> consume(String name) {
		return Uni.createFrom().item(() -> {
			return "hello " + name.toUpperCase();
		});
	}

}
