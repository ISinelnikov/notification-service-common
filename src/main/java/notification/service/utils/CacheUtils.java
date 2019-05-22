package notification.service.utils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

public final class CacheUtils {
    private static final int DEFAULT_EXECUTOR_MAX_SIZE = Integer.getInteger("cache.utils.default.executor.max.size", 10);

    private CacheUtils() {
    }

    public static <K, V> LoadingCache<K, V> buildLoadingCache(int refreshDuration,
            Function<K, V> loader, @Nullable ExecutorService executorService) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(DEFAULT_EXECUTOR_MAX_SIZE);
        }

        return Caffeine.newBuilder()
                .executor(executorService)
                .expireAfterWrite(refreshDuration, TimeUnit.MINUTES)
                .build(loader::apply);
    }

    @Nullable
    public static <V> V getValue(@Nullable Optional<V> optional) {
        return optional != null ? optional.orElse(null) : null;
    }

    public static <K, V> List<V> updateListByMap(List<V> fireList, ConcurrentMap<K, Optional<V>> fireCache) {
        List<V> currentCacheValues = fireCache
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        fireList.clear();
        fireList.addAll(currentCacheValues);
        return fireList;
    }
}
