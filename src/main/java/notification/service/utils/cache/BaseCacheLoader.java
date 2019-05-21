package notification.service.utils.cache;

import java.util.function.Function;
import com.google.common.cache.CacheLoader;

public final class BaseCacheLoader<K, V> extends CacheLoader<K, V> {
    private final Function<K, V> loader;

    public static <K, V> BaseCacheLoader<K, V> of(Function<K, V> loader) {
        return new BaseCacheLoader<>(loader);
    }

    public BaseCacheLoader(Function<K, V> loader) {
        this.loader = loader;
    }

    @Override
    public V load(K key) {
        return loader.apply(key);
    }
}
