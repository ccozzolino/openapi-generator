package org.openapitools.server.api;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.MultiMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.handler.impl.UserHolder;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;

public abstract class ApiMessageBuilderBase implements ApiMessageBuilder {
    public static final String AUTH_USER_HEADER_KEY = "AUTH_USER";
    public static final String AUTH_PROVIDER_NAME_HEADER_KEY = "AUTH_PROVIDER_NAME";

    protected void addAuthUserHeader(RoutingContext context, DeliveryOptions deliveryOptions) {
        Buffer buffer = Buffer.buffer();
        new UserHolder(context).writeToBuffer(buffer);
        deliveryOptions.addHeader(AUTH_USER_HEADER_KEY, buffer.toString());
        String authProviderName = context.get(AUTH_PROVIDER_NAME_HEADER_KEY);
        if (authProviderName != null) {
            deliveryOptions.addHeader(AUTH_PROVIDER_NAME_HEADER_KEY, authProviderName);
        }
    }

    protected User extractAuthUserFromMessage(Message<?> message) {
        User user = null;
        String serializedUser = message.headers().get(AUTH_USER_HEADER_KEY);
        if (serializedUser != null && !serializedUser.isEmpty()) {
            Buffer buffer = Buffer.buffer(serializedUser);
            UserHolder userHolder = new UserHolder();
            userHolder.readFromBuffer(0, buffer);
            user = userHolder.user;
            if (user != null) {
                String authProviderName = message.headers().get(AUTH_PROVIDER_NAME_HEADER_KEY);
                if (authProviderName != null) {
                    user.setAuthProvider(AuthProviderRegistry.getInstance().get(authProviderName));
                }
            }
        }
        return user;
    }

    @Override
    public DeliveryOptions configureDeliveryOptions() {
        return new DeliveryOptions();
    }

    @Override
    public ApiMessage buildMessage(RoutingContext context) {
        RequestParameters requestParameters = context.get("parsedParameters");

        JsonObject content = getContentExtractor().extract(requestParameters);

        DeliveryOptions deliveryOptions = configureDeliveryOptions();

        addAuthUserHeader(context, deliveryOptions);

        context.request().headers().forEach(entry -> deliveryOptions.addHeader(entry.getKey(), entry.getValue()));

        return new ApiMessage(deliveryOptions, content);
    }

    protected abstract ContentExtractor getContentExtractor();
}
