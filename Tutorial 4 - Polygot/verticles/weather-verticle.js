var Router = require("vertx-web-js/router");
var weatherService = require('./weather-services.js');

var port = 8080;
var router = Router.router(vertx);

router.route("/greet/:name").handler(function (ctx) {
  var name = ctx.request().getParam("name");
  ctx.response().end("Hello, " + name + "!!!");
});

router.route("/weather").handler(weatherService.fetch);

vertx.createHttpServer().requestHandler(router.accept).listen(port, function(res, err) {
	if (res.succeeded) {
		console.log("Failed to start server!");
	} else {
		console.log("Server started at port " + port);
	}
});