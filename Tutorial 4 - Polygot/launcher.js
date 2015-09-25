module.exports = {
    vertxStartAsync : function(startFuture) {
        vertx.deployVerticle("verticles/weather-verticle.js", function(res, err) {
            if (err) {
                startFuture.fail();
            } else {
                startFuture.complete();
            }
        });
    }
};

