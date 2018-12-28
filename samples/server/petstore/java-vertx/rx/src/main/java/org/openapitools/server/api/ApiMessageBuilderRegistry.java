package org.openapitools.server.api;

public class ApiMessageBuilderRegistry extends Registry<ApiMessageBuilder> {
    private static ApiMessageBuilderRegistry instance = new ApiMessageBuilderRegistry();

    private ApiMessageBuilderRegistry() { }

    public static ApiMessageBuilderRegistry getInstance() {
        return instance;
    }
}
