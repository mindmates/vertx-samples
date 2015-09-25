package com.equinix.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class ApplicationVerticle extends AbstractVerticle {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void start() throws Exception {
		int port = 8080;
		
		Router router = Router.router(vertx);
		
		router.route("/greet/:name").handler(ctx -> {
			String name = ctx.request().getParam("name");
			
			ctx.response().end("Hello, " + name + "!!!");
		});
		
		vertx.createHttpServer().requestHandler(router::accept).listen(port, res -> {
			if (res.succeeded()) {
				logger.info("Server started at port {}", port);
			} else {
				logger.error("Failed to start server!");
			}
		});

	}
	
	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(ApplicationVerticle.class.getName());		
	}
}
