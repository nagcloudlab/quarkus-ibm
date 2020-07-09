package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {

//		vertx.createHttpServer().requestHandler(req -> {
//			req.response().end("Hello vert.x");
//		}).listen(8080);

//		vertx.createHttpServer().requestHandler(req -> {
//			if (req.path().startsWith("/api/v1/hello"))
//				req.response().end("Hello vert.x");
//		}).listen(8080);

		// Routing

		DeploymentOptions opts = new DeploymentOptions().setWorker(true).setInstances(8);

		vertx.deployVerticle("io.vertx.example.HelloVerticle", opts);

		Router router = Router.router(vertx);

		router.get("/api/v1/hello").handler(this::sayHello);

		router.get("/api/v1/hello/:name").handler(this::sayHelloWithName);

		vertx.createHttpServer().requestHandler(router).listen(8080);

	}

	public void sayHello(RoutingContext ctx) {
//		ctx.request().response().end("Hello vert.x");
		System.out.println(Thread.currentThread().getName() + " : MainVerticle");
		vertx.eventBus().request("hello.vertx.addr", "", reply -> {
			ctx.request().response().end((String) reply.result().body());
		});
	}

	public void sayHelloWithName(RoutingContext ctx) {
		String name = ctx.pathParam("name");
//		ctx.request().response().end(String.format("Hello vert.x %s", name));

		vertx.eventBus().request("hello.name.vertx.addr", name, reply -> {
			ctx.request().response().end((String) reply.result().body());
		});

	}

}
