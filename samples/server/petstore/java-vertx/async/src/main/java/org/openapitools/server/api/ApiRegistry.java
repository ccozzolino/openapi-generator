package org.openapitools.server.api;

import org.openapitools.server.api.Registry;

public class ApiRegistry extends Registry<Api> {
    private static ApiRegistry instance = new ApiRegistry();

    private ApiRegistry() { }

    public static ApiRegistry getInstance() {
        return instance;
    }
}