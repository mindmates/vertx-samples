package com.equinix.tutorial.controller;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class WeatherController {
	public void fetch(RoutingContext ctx) {
		ctx.vertx().createHttpClient().getNow("api.openweathermap.org", "/data/2.5/weather?q=sunnyvale", res -> {
			res.bodyHandler(buffer -> {
				JsonObject json = new JsonObject(buffer.toString());
				String city = json.getString("name");
				Double temperature = json.getJsonObject("main").getDouble("temp");
				
				ctx.response().end("Welcome to " + city + ". The temperature is " + temperature + " Fahrenheit");
			});
		});
	}
}
