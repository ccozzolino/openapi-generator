package org.openapitools.server.api;

import io.vertx.core.json.JsonObject;
import io.vertx.core.eventbus.DeliveryOptions;

public class ApiMessage {
    private final DeliveryOptions deliveryOptions;

    private final JsonObject content;

    public ApiMessage(DeliveryOptions deliveryOptions, JsonObject content) {
        this.deliveryOptions = deliveryOptions;
        this.content = content;
    }

    public DeliveryOptions getDeliveryOptions() {
        return deliveryOptions;
    }

    public JsonObject getContent() {
        return content;
    }
}
