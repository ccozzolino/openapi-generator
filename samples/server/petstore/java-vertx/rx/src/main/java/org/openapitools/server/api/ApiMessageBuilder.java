package org.openapitools.server.api;

import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.ext.web.RoutingContext;

public interface ApiMessageBuilder {
    DeliveryOptions configureDeliveryOptions();

    ApiMessage buildMessage(RoutingContext context);
}
