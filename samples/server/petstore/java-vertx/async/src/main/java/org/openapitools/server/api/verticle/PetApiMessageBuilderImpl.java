package org.openapitools.server.api.verticle;

import java.util.Map;
import java.util.HashMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameter;
import io.vertx.ext.web.api.RequestParameters;

import org.openapitools.server.api.ApiMessageBuilderBase;
import org.openapitools.server.api.ContentExtractor;

public class PetApiMessageBuilderImpl extends ApiMessageBuilderBase {
    private static class ContentExtractor_addPet implements ContentExtractor {
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
    private static class ContentExtractor_deletePet implements ContentExtractor {
        private JsonObject headerParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("apiKey", requestParameters.queryParameter("apiKey").getString());

            return jsonObject;
        }
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("petId", requestParameters.queryParameter("petId").getLong());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);
          jsonObject = jsonObject.mergeIn(headerParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_findPetsByStatus implements ContentExtractor {
        private JsonObject queryParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
              jsonObject = jsonObject.put("status", requestParameters.queryParameter("status").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(queryParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_findPetsByTags implements ContentExtractor {
        private JsonObject queryParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
              jsonObject = jsonObject.put("tags", requestParameters.queryParameter("tags").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(queryParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_getPetById implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("petId", requestParameters.queryParameter("petId").getLong());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_updatePet implements ContentExtractor {
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
    private static class ContentExtractor_updatePetWithForm implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("petId", requestParameters.queryParameter("petId").getLong());

            return jsonObject;
        }
        private JsonObject formParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("name", requestParameters.queryParameter("name").getString());

            jsonObject = jsonObject.put("status", requestParameters.queryParameter("status").getString());

            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);
          jsonObject = jsonObject.mergeIn(formParameters(requestParameters), true);

          return jsonObject;
        }
    }
    private static class ContentExtractor_uploadFile implements ContentExtractor {
        private JsonObject pathParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("petId", requestParameters.queryParameter("petId").getLong());

            return jsonObject;
        }
        private JsonObject formParameters(RequestParameters requestParameters) {
            JsonObject jsonObject = new JsonObject();
            jsonObject = jsonObject.put("additionalMetadata", requestParameters.queryParameter("additionalMetadata").getString());


            return jsonObject;
        }

        public JsonObject extract(RequestParameters requestParameters) {
          JsonObject jsonObject = new JsonObject();

          jsonObject = jsonObject.mergeIn(pathParameters(requestParameters), true);
          jsonObject = jsonObject.mergeIn(formParameters(requestParameters), true);

          return jsonObject;
        }
    }

    private final ContentExtractor contentExtractor;
    private final static Map<String, ContentExtractor> contentExtractors;

    static {
        contentExtractors = new HashMap<>();
        contentExtractors.put("addPet", new ContentExtractor_addPet());
        contentExtractors.put("deletePet", new ContentExtractor_deletePet());
        contentExtractors.put("findPetsByStatus", new ContentExtractor_findPetsByStatus());
        contentExtractors.put("findPetsByTags", new ContentExtractor_findPetsByTags());
        contentExtractors.put("getPetById", new ContentExtractor_getPetById());
        contentExtractors.put("updatePet", new ContentExtractor_updatePet());
        contentExtractors.put("updatePetWithForm", new ContentExtractor_updatePetWithForm());
        contentExtractors.put("uploadFile", new ContentExtractor_uploadFile());
    }

    public PetApiMessageBuilderImpl(String operationId) {
        this.contentExtractor = contentExtractors.get(operationId);
    }

    protected ContentExtractor getContentExtractor() {
        return contentExtractor;
    }
}
