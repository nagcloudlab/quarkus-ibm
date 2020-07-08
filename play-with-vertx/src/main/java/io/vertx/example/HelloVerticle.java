package io.vertx.example;

import java.util.concurrent.TimeUnit;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("hello.vertx.addr", msg -> {
			// 10s
			System.out.println(Thread.currentThread().getName() + " : HelloVerticle");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msg.reply("Hello vert.x");
		});

		vertx.eventBus().consumer("hello.name.vertx.addr", msg -> {
			// 3s
			String name = (String) msg.body();
			msg.reply(String.format("Hello vert.x %s", name));
		});

	}

}
