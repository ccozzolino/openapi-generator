package org.openapitools.server.api;

import io.vertx.ext.auth.AuthProvider;

public class AuthProviderRegistry extends Registry<AuthProvider> {
    private static AuthProviderRegistry instance = new AuthProviderRegistry();

    private AuthProviderRegistry() { }

    public static AuthProviderRegistry getInstance() {
        return instance;
    }
}
