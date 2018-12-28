package org.openapitools.server.api;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.Router;
import java.util.ArrayList;
import java.util.List;

import org.openapitools.server.api.Api;
import org.openapitools.server.api.ApiRegistry;
import org.openapitools.server.api.ApiMessageBuilderRegistry;
import org.openapitools.server.api.AuthProviderRegistry;
import org.openapitools.server.api.RouteHandler;

public class MainApiVerticle extends AbstractVerticle {
    final static Logger LOGGER = LoggerFactory.getLogger(MainApiVerticle.class);
    final static Integer DEFAULT_HTTP_PORT = Integer.parseInt(System.getProperty("default.http.port", "8080"));

    protected void initApis(Vertx vertx) throws Exception {
        
        ApiRegistry.getInstance()
            .register("org.openapitools.server.api.verticle.PetApiImpl",
                      (Api) getClass().getClassLoader().loadClass("org.openapitools.server.api.verticle.PetApiImpl").newInstance());
        
        ApiRegistry.getInstance()
            .register("org.openapitools.server.api.verticle.StoreApiImpl",
                      (Api) getClass().getClassLoader().loadClass("org.openapitools.server.api.verticle.StoreApiImpl").newInstance());
        
        ApiRegistry.getInstance()
            .register("org.openapitools.server.api.verticle.UserApiImpl",
                      (Api) getClass().getClassLoader().loadClass("org.openapitools.server.api.verticle.UserApiImpl").newInstance());
        
    }

    protected void initMessageBuilders(Vertx vertx) throws Exception {
        
        
        String addPetOperationId = "addPet";
        ApiMessageBuilderRegistry.getInstance()
            .register(addPetOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(addPetOperationId));
        
        String deletePetOperationId = "deletePet";
        ApiMessageBuilderRegistry.getInstance()
            .register(deletePetOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(deletePetOperationId));
        
        String findPetsByStatusOperationId = "findPetsByStatus";
        ApiMessageBuilderRegistry.getInstance()
            .register(findPetsByStatusOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(findPetsByStatusOperationId));
        
        String findPetsByTagsOperationId = "findPetsByTags";
        ApiMessageBuilderRegistry.getInstance()
            .register(findPetsByTagsOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(findPetsByTagsOperationId));
        
        String getPetByIdOperationId = "getPetById";
        ApiMessageBuilderRegistry.getInstance()
            .register(getPetByIdOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(getPetByIdOperationId));
        
        String updatePetOperationId = "updatePet";
        ApiMessageBuilderRegistry.getInstance()
            .register(updatePetOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(updatePetOperationId));
        
        String updatePetWithFormOperationId = "updatePetWithForm";
        ApiMessageBuilderRegistry.getInstance()
            .register(updatePetWithFormOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(updatePetWithFormOperationId));
        
        String uploadFileOperationId = "uploadFile";
        ApiMessageBuilderRegistry.getInstance()
            .register(uploadFileOperationId,
                      new org.openapitools.server.api.verticle.PetApiMessageBuilderImpl(uploadFileOperationId));
        
        
        
        String deleteOrderOperationId = "deleteOrder";
        ApiMessageBuilderRegistry.getInstance()
            .register(deleteOrderOperationId,
                      new org.openapitools.server.api.verticle.StoreApiMessageBuilderImpl(deleteOrderOperationId));
        
        String getInventoryOperationId = "getInventory";
        ApiMessageBuilderRegistry.getInstance()
            .register(getInventoryOperationId,
                      new org.openapitools.server.api.verticle.StoreApiMessageBuilderImpl(getInventoryOperationId));
        
        String getOrderByIdOperationId = "getOrderById";
        ApiMessageBuilderRegistry.getInstance()
            .register(getOrderByIdOperationId,
                      new org.openapitools.server.api.verticle.StoreApiMessageBuilderImpl(getOrderByIdOperationId));
        
        String placeOrderOperationId = "placeOrder";
        ApiMessageBuilderRegistry.getInstance()
            .register(placeOrderOperationId,
                      new org.openapitools.server.api.verticle.StoreApiMessageBuilderImpl(placeOrderOperationId));
        
        
        
        String createUserOperationId = "createUser";
        ApiMessageBuilderRegistry.getInstance()
            .register(createUserOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(createUserOperationId));
        
        String createUsersWithArrayInputOperationId = "createUsersWithArrayInput";
        ApiMessageBuilderRegistry.getInstance()
            .register(createUsersWithArrayInputOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(createUsersWithArrayInputOperationId));
        
        String createUsersWithListInputOperationId = "createUsersWithListInput";
        ApiMessageBuilderRegistry.getInstance()
            .register(createUsersWithListInputOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(createUsersWithListInputOperationId));
        
        String deleteUserOperationId = "deleteUser";
        ApiMessageBuilderRegistry.getInstance()
            .register(deleteUserOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(deleteUserOperationId));
        
        String getUserByNameOperationId = "getUserByName";
        ApiMessageBuilderRegistry.getInstance()
            .register(getUserByNameOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(getUserByNameOperationId));
        
        String loginUserOperationId = "loginUser";
        ApiMessageBuilderRegistry.getInstance()
            .register(loginUserOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(loginUserOperationId));
        
        String logoutUserOperationId = "logoutUser";
        ApiMessageBuilderRegistry.getInstance()
            .register(logoutUserOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(logoutUserOperationId));
        
        String updateUserOperationId = "updateUser";
        ApiMessageBuilderRegistry.getInstance()
            .register(updateUserOperationId,
                      new org.openapitools.server.api.verticle.UserApiMessageBuilderImpl(updateUserOperationId));
        
        
    }

    protected Router createRouter(Vertx vertx, OpenAPI3RouterFactory openAPI3RouterFactory) {
        
        
        String addPetOperationId = "addPet";
        openAPI3RouterFactory.addHandlerByOperationId(addPetOperationId, new RouteHandler(vertx, addPetOperationId));
        
        String deletePetOperationId = "deletePet";
        openAPI3RouterFactory.addHandlerByOperationId(deletePetOperationId, new RouteHandler(vertx, deletePetOperationId));
        
        String findPetsByStatusOperationId = "findPetsByStatus";
        openAPI3RouterFactory.addHandlerByOperationId(findPetsByStatusOperationId, new RouteHandler(vertx, findPetsByStatusOperationId));
        
        String findPetsByTagsOperationId = "findPetsByTags";
        openAPI3RouterFactory.addHandlerByOperationId(findPetsByTagsOperationId, new RouteHandler(vertx, findPetsByTagsOperationId));
        
        String getPetByIdOperationId = "getPetById";
        openAPI3RouterFactory.addHandlerByOperationId(getPetByIdOperationId, new RouteHandler(vertx, getPetByIdOperationId));
        
        String updatePetOperationId = "updatePet";
        openAPI3RouterFactory.addHandlerByOperationId(updatePetOperationId, new RouteHandler(vertx, updatePetOperationId));
        
        String updatePetWithFormOperationId = "updatePetWithForm";
        openAPI3RouterFactory.addHandlerByOperationId(updatePetWithFormOperationId, new RouteHandler(vertx, updatePetWithFormOperationId));
        
        String uploadFileOperationId = "uploadFile";
        openAPI3RouterFactory.addHandlerByOperationId(uploadFileOperationId, new RouteHandler(vertx, uploadFileOperationId));
        
        
        
        String deleteOrderOperationId = "deleteOrder";
        openAPI3RouterFactory.addHandlerByOperationId(deleteOrderOperationId, new RouteHandler(vertx, deleteOrderOperationId));
        
        String getInventoryOperationId = "getInventory";
        openAPI3RouterFactory.addHandlerByOperationId(getInventoryOperationId, new RouteHandler(vertx, getInventoryOperationId));
        
        String getOrderByIdOperationId = "getOrderById";
        openAPI3RouterFactory.addHandlerByOperationId(getOrderByIdOperationId, new RouteHandler(vertx, getOrderByIdOperationId));
        
        String placeOrderOperationId = "placeOrder";
        openAPI3RouterFactory.addHandlerByOperationId(placeOrderOperationId, new RouteHandler(vertx, placeOrderOperationId));
        
        
        
        String createUserOperationId = "createUser";
        openAPI3RouterFactory.addHandlerByOperationId(createUserOperationId, new RouteHandler(vertx, createUserOperationId));
        
        String createUsersWithArrayInputOperationId = "createUsersWithArrayInput";
        openAPI3RouterFactory.addHandlerByOperationId(createUsersWithArrayInputOperationId, new RouteHandler(vertx, createUsersWithArrayInputOperationId));
        
        String createUsersWithListInputOperationId = "createUsersWithListInput";
        openAPI3RouterFactory.addHandlerByOperationId(createUsersWithListInputOperationId, new RouteHandler(vertx, createUsersWithListInputOperationId));
        
        String deleteUserOperationId = "deleteUser";
        openAPI3RouterFactory.addHandlerByOperationId(deleteUserOperationId, new RouteHandler(vertx, deleteUserOperationId));
        
        String getUserByNameOperationId = "getUserByName";
        openAPI3RouterFactory.addHandlerByOperationId(getUserByNameOperationId, new RouteHandler(vertx, getUserByNameOperationId));
        
        String loginUserOperationId = "loginUser";
        openAPI3RouterFactory.addHandlerByOperationId(loginUserOperationId, new RouteHandler(vertx, loginUserOperationId));
        
        String logoutUserOperationId = "logoutUser";
        openAPI3RouterFactory.addHandlerByOperationId(logoutUserOperationId, new RouteHandler(vertx, logoutUserOperationId));
        
        String updateUserOperationId = "updateUser";
        openAPI3RouterFactory.addHandlerByOperationId(updateUserOperationId, new RouteHandler(vertx, updateUserOperationId));
        
        
        AuthProvider api_keyAuthProvider = AuthProviderRegistry.getInstance().get("api_key");
        if (api_keyAuthProvider != null) {
            openAPI3RouterFactory.addSecurityHandler("api_key", ApiKeyAuthHandler.create(api_keyAuthProvider, ApiKeyAuthHandler.Location.HEADER, "api_key"));
        }

        openAPI3RouterFactory.addSecurityHandler("petstore_auth:write:pets",
            OAuth2AuthHandler.create(AuthProviderRegistry.getInstance().get("petstore_auth:write:pets")));
        openAPI3RouterFactory.addSecurityHandler("petstore_auth:read:pets",
            OAuth2AuthHandler.create(AuthProviderRegistry.getInstance().get("petstore_auth:read:pets")));

        return openAPI3RouterFactory.getRouter();
    }

    protected HttpServerOptions createHttpServerOptions(Vertx vertx) {
        HttpServerOptions httpServerOptions = new HttpServerOptions();

        Integer serverPort = vertx.getOrCreateContext().config().getInteger("http.port", DEFAULT_HTTP_PORT);
        httpServerOptions.setPort(serverPort);

        return httpServerOptions;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Json.mapper.registerModule(new JavaTimeModule());
        OpenAPI3RouterFactory.create(vertx, "openapi.json", handler -> {
            try {
                if (handler.succeeded()) {
                    Router router = createRouter(vertx, handler.result());

                    initApis(vertx);
                    initMessageBuilders(vertx);
                    deployVerticles(startFuture);

                    HttpServerOptions httpServerOptions = createHttpServerOptions(vertx);
                    vertx.createHttpServer(httpServerOptions)
                        .requestHandler(router::accept)
                        .listen(listenHandler -> {
                            if (listenHandler.succeeded()) {
                                startFuture.complete();
                            } else {
                                startFuture.fail(listenHandler.cause());
                            }
                        });
                } else {
                    startFuture.fail(handler.cause());
                }
            } catch (Throwable e) {
                LOGGER.error("MainApiVerticle : start failure", e);
                startFuture.fail(e);
            }
        });
    }

    public void deployVerticles(Future<Void> startFuture) {
        List<Future> deploymentFutures = new ArrayList<>();

        
        Handler<AsyncResult<String>> PetApiHandler = res -> {
            if (!res.succeeded()) {
                LOGGER.error("PetApiVerticle : Deployment failed");
            }
        };
        Future<String> PetApiFuture = Future.future();
        vertx.deployVerticle("org.openapitools.server.api.verticle.PetApiVerticle", PetApiFuture.completer());
        deploymentFutures.add(PetApiFuture);

        CompositeFuture.all(deploymentFutures).setHandler(res -> {
            if (res.succeeded()) {
                res.result().list().forEach(id -> LOGGER.info("Deployed [id: " + id + "]"));
            } else {
                LOGGER.error("PetApiVerticle : Deployment failed [cause: " + res.cause() + "]");
                startFuture.fail(res.cause());
            }
        });
        
        Handler<AsyncResult<String>> StoreApiHandler = res -> {
            if (!res.succeeded()) {
                LOGGER.error("StoreApiVerticle : Deployment failed");
            }
        };
        Future<String> StoreApiFuture = Future.future();
        vertx.deployVerticle("org.openapitools.server.api.verticle.StoreApiVerticle", StoreApiFuture.completer());
        deploymentFutures.add(StoreApiFuture);

        CompositeFuture.all(deploymentFutures).setHandler(res -> {
            if (res.succeeded()) {
                res.result().list().forEach(id -> LOGGER.info("Deployed [id: " + id + "]"));
            } else {
                LOGGER.error("StoreApiVerticle : Deployment failed [cause: " + res.cause() + "]");
                startFuture.fail(res.cause());
            }
        });
        
        Handler<AsyncResult<String>> UserApiHandler = res -> {
            if (!res.succeeded()) {
                LOGGER.error("UserApiVerticle : Deployment failed");
            }
        };
        Future<String> UserApiFuture = Future.future();
        vertx.deployVerticle("org.openapitools.server.api.verticle.UserApiVerticle", UserApiFuture.completer());
        deploymentFutures.add(UserApiFuture);

        CompositeFuture.all(deploymentFutures).setHandler(res -> {
            if (res.succeeded()) {
                res.result().list().forEach(id -> LOGGER.info("Deployed [id: " + id + "]"));
            } else {
                LOGGER.error("UserApiVerticle : Deployment failed [cause: " + res.cause() + "]");
                startFuture.fail(res.cause());
            }
        });
        
    }
}
