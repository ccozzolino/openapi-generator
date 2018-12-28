package org.openapitools.server.api.verticle;

import java.util.Map;
import java.util.HashMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameter;
import io.vertx.ext.web.api.RequestParameters;

import org.openapitools.server.api.ApiMessageBuilderBase;
import org.openapitools.server.api.ContentExtractor;

public class UserApiMessageBuilderImpl extends ApiMessageBuilderBase {
    private static class ContentExtractor_createUser implements ContentExtractor {
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
    private static class ContentExtractor_createUsersWithArrayInput implements ContentExtractor {
        private JsonObject body(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("body", requestParameters.body().getJsonArray());
            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(body(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_createUsersWithListInput implements ContentExtractor {
        private JsonObject body(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("body", requestParameters.body().getJsonArray());
            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(body(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_deleteUser implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("username", requestParameters.queryParameter("username").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_getUserByName implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("username", requestParameters.queryParameter("username").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_loginUser implements ContentExtractor {
        private JsonObject queryParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("username", requestParameters.queryParameter("username").getString());

            jsonObject = jsonObject.put("password", requestParameters.queryParameter("password").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(queryParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_updateUser implements ContentExtractor {
        private JsonObject body(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("body", requestParameters.body().getJsonObject());
            return jsonObject;
        }
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("username", requestParameters.queryParameter("username").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);
          jsonObject = jsonObject.mergeIn(body(requestParameters), true);

          return jsonObject;
        }
    }

    private final ContentExtractor contentExtractor;
    private final static Map<String, ContentExtractor> contentExtractors;

    static {
        contentExtractors = new HashMap<>();
        contentExtractors.put("createUser", new ContentExtractor_createUser());
        contentExtractors.put("createUsersWithArrayInput", new ContentExtractor_createUsersWithArrayInput());
        contentExtractors.put("createUsersWithListInput", new ContentExtractor_createUsersWithListInput());
        contentExtractors.put("deleteUser", new ContentExtractor_deleteUser());
        contentExtractors.put("getUserByName", new ContentExtractor_getUserByName());
        contentExtractors.put("loginUser", new ContentExtractor_loginUser());
        contentExtractors.put("updateUser", new ContentExtractor_updateUser());
    }

    public UserApiMessageBuilderImpl(String operationId) {
        this.contentExtractor = contentExtractors.get(operationId);
    }

    protected ContentExtractor getContentExtractor() {
        return contentExtractor;
    }
}
