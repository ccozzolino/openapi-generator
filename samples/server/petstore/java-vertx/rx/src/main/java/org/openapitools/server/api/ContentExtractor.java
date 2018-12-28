package org.openapitools.server.api;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameters;

public interface ContentExtractor {
    JsonObject extract(RequestParameters context);
}
