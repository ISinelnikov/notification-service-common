package notification.service.utils.cache;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import static com.google.common.cache.CacheLoader.asyncReloading;

public final class CacheUtils {
    //-- Refresh times
    public static final long ONE_SECOND = 1_000;
    public static final long ONE_MINUTE = 60 * ONE_SECOND;
    public static final long FIVE_MINUTES = 5 * ONE_MINUTE;
    public static final long TEN_MINUTES = 2 * FIVE_MINUTES;

    private static final int DEFAULT_EXECUTOR_MAX_SIZE = 10;

    private CacheUtils() {
    }

    public static <K, V> LoadingCache<K, V> buildLoadingCache(int expireDuration, int refreshDuration,
            Function<K, V> loader) {
        ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_EXECUTOR_MAX_SIZE);

        CacheLoader<K, V> cacheLoader = asyncReloading(BaseCacheLoader.of(loader), executor);
        return CacheBuilder.newBuilder().expireAfterAccess(expireDuration, TimeUnit.MINUTES)
                .refreshAfterWrite(refreshDuration, TimeUnit.MINUTES).build(cacheLoader);
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
