package org.openapitools.server.api.verticle;

import org.openapitools.server.api.MainApiException;
import org.openapitools.server.api.model.Order;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import org.openapitools.server.api.Api;

import java.util.List;
import java.util.Map;

public interface StoreApi extends Api {
    //deleteOrder
    void deleteOrder(String orderId, Handler<AsyncResult<Void>> handler);

    //getInventory
    void getInventory(Handler<AsyncResult<Map<String, Integer>>> handler);

    //getOrderById
    void getOrderById(Long orderId, Handler<AsyncResult<Order>> handler);

    //placeOrder
    void placeOrder(Order body, Handler<AsyncResult<Order>> handler);

}
