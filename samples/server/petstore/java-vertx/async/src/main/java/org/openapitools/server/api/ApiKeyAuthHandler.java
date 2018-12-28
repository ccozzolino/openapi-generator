package org.openapitools.server.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.AuthHandlerImpl;
import io.vertx.ext.web.handler.impl.HttpStatusException;

public class ApiKeyAuthHandler extends AuthHandlerImpl {
    private static final HttpStatusException UNAUTHORIZED = new HttpStatusException(401);

    public static final String API_KEY_NAME_PARAM = "name";
    public static final String API_KEY_VALUE_PARAM = "value";

    private final String name;
    private final ApiKeyAuthHandler.Location location;

    enum Location {
        HEADER, QUERY
    }

    static ApiKeyAuthHandler create(AuthProvider authProvider, ApiKeyAuthHandler.Location location, String name) {
        return new ApiKeyAuthHandler(authProvider, location, name);
    }

    ApiKeyAuthHandler(AuthProvider authProvider, ApiKeyAuthHandler.Location location, String name) {
        super(authProvider);
        this.location = location;
        this.name = name;
    }

    @Override
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        HttpServerRequest request = context.request();

        String value = null;
        switch (this.location) {
            case QUERY:
                value = request.getParam(this.name);
                break;
            case HEADER:
                value = request.headers().get(this.name);
                break;
            default:
                handler.handle(Future.failedFuture(UNAUTHORIZED));
                return;
        }

        if (value == null) {
            handler.handle(Future.failedFuture(UNAUTHORIZED));
            return;
        }

        handler.handle(Future.succeededFuture((new JsonObject())
            .put(API_KEY_NAME_PARAM, name)
            .put(API_KEY_VALUE_PARAM, value)));
    }
}
