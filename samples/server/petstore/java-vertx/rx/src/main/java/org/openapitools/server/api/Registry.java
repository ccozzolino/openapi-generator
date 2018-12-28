package org.openapitools.server.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class Registry<T> {

    private Map<String, T> items = new ConcurrentHashMap<>();

    private Map<String, Supplier<T>> factories = new ConcurrentHashMap<>();

    public void registerFactory(String name, Supplier<T> factory) {
        factories.put(name, factory);
    }

    public void register(String name, T item) {
        items.put(name, item);
    }

    public T get(String name) {
        return items.computeIfAbsent(name, n ->
            factories.getOrDefault(n, () -> null).get()
        );
    }
}
