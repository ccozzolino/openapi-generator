package org.openapitools.server.api.verticle;

import java.util.Map;
import java.util.HashMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameter;
import io.vertx.ext.web.api.RequestParameters;

import org.openapitools.server.api.ApiMessageBuilderBase;
import org.openapitools.server.api.ContentExtractor;

public class StoreApiMessageBuilderImpl extends ApiMessageBuilderBase {
    private static class ContentExtractor_deleteOrder implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("orderId", requestParameters.queryParameter("orderId").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_getOrderById implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("orderId", requestParameters.queryParameter("orderId").getLong());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_placeOrder implements ContentExtractor {
        private JsonObject body(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("body", requestParameters.body().getJsonObject());
            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(body(requestParameters), true);

          return jsonObject;
        }
    }

    private final ContentExtractor contentExtractor;
    private final static Map<String, ContentExtractor> contentExtractors;

    static {
        contentExtractors = new HashMap<>();
        contentExtractors.put("deleteOrder", new ContentExtractor_deleteOrder());
        contentExtractors.put("getOrderById", new ContentExtractor_getOrderById());
        contentExtractors.put("placeOrder", new ContentExtractor_placeOrder());
    }

    public StoreApiMessageBuilderImpl(String operationId) {
        this.contentExtractor = contentExtractors.get(operationId);
    }

    protected ContentExtractor getContentExtractor() {
        return contentExtractor;
    }
}
