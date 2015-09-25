exports.fetch = function(ctx) {
	vertx.createHttpClient().getNow("api.openweathermap.org", "/data/2.5/weather?q=sunnyvale", function(res) {
		res.bodyHandler(function(buffer) {
			var json = JSON.parse(buffer.toString('UTF-8'));
			var city = json.name
			var temperature = json.main.temp;

			ctx.response().end("Welcome to " + city + ". The temperature is " + temperature + " Fahrenheit");
		});
	});
}