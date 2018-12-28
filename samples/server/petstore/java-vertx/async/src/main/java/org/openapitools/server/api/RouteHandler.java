package org.openapitools.server.api;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.handler.impl.UserHolder;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public class RouteHandler implements Handler<RoutingContext> {
    public static final String CUSTOM_STATUS_CODE_HEADER_KEY = "CUSTOM_STATUS_CODE";
    public static final String CUSTOM_STATUS_MESSAGE_HEADER_KEY = "CUSTOM_STATUS_MESSAGE";

    static final Logger log = LoggerFactory.getLogger(RouteHandler.class);

    private Vertx vertx;
    private String operationId;

    public RouteHandler(Vertx vertx, String operationId) {
        this.vertx = vertx;
        this.operationId = operationId;
    }

    private static void manageError(ReplyException cause, HttpServerResponse response) {
        if(isExistingHttStatusCode(cause.failureCode())) {
            response.setStatusCode(cause.failureCode());
            if(StringUtils.isNotEmpty(cause.getMessage())) {
                response.setStatusMessage(cause.getMessage());
            }
        } else {
            response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
        }
        response.end();
    }

    private static boolean isExistingHttStatusCode(int failureCode) {
        try {
            HttpResponseStatus.valueOf(failureCode);
        } catch (IllegalArgumentException e) {
            log.info(failureCode + " is not a valid HttpStatusCode", e);
            return false;
        }
        return true;
    }

    private static void badRequestEnd(HttpServerResponse response) {
        response.setStatusCode(400).setStatusMessage("Bad Request").end();
    }

    protected void manageHeaders(HttpServerResponse httpServerResponse, MultiMap messageHeaders) {
        if(messageHeaders.contains(CUSTOM_STATUS_CODE_HEADER_KEY)) {
            Integer customStatusCode = Integer.valueOf(messageHeaders.get(CUSTOM_STATUS_CODE_HEADER_KEY));
            httpServerResponse.setStatusCode(customStatusCode);
            messageHeaders.remove(CUSTOM_STATUS_CODE_HEADER_KEY);
        }
        if(messageHeaders.contains(CUSTOM_STATUS_MESSAGE_HEADER_KEY)) {
            String customStatusMessage = messageHeaders.get(CUSTOM_STATUS_MESSAGE_HEADER_KEY);
            httpServerResponse.setStatusMessage(customStatusMessage);
            messageHeaders.remove(CUSTOM_STATUS_MESSAGE_HEADER_KEY);
        }
        httpServerResponse.headers().addAll(messageHeaders);
    }

    @Override
    public void handle(RoutingContext context) {
        try {
            ApiMessageBuilder apiMessageBuilder = ApiMessageBuilderRegistry.getInstance().get(operationId);
            if (apiMessageBuilder == null) {
                throw new IllegalStateException("No api message builder supplied for operation: " + operationId);
            }

            ApiMessage message = apiMessageBuilder.buildMessage(context);

            vertx.eventBus().<String> send(operationId, message.getContent(), message.getDeliveryOptions(), operationResponse -> {
                if (operationResponse.succeeded()) {
                    manageHeaders(context.response(), operationResponse.result().headers());

                    if (operationResponse.result().body() != null) {
                        context.response().end(operationResponse.result().body());
                    } else {
                        context.response().end();
                    }

                } else {
                    log.error("Internal Server Error", operationResponse.cause());
                    manageError((ReplyException)operationResponse.cause(), context.response());
                }
            });
        } catch (Exception e) {
            log.error("sending Bad Request", e);
            badRequestEnd(context.response());
        }
    }
}
