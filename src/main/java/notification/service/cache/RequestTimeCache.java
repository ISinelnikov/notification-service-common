package notification.service.cache;

import notification.service.utils.CacheUtils;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class RequestTimeCache {
    private static final Logger logger = LoggerFactory.getLogger(RequestTimeCache.class);

    private final LoadingCache<String, RequestTimeData> ipToRequestData;

    private final long guardValue;

    public RequestTimeCache(long guardValue, int refreshDuration, ExecutorService executorService) {
        this.guardValue = guardValue;

        ipToRequestData = CacheUtils.buildLoadingCache(refreshDuration,
                RequestTimeData::new, executorService);
    }

    public boolean isEnabledRequest(String sourceIp) {
        RequestTimeData requestData = ipToRequestData.get(sourceIp);

        //Get method in loading cache declared as nullable
        if (requestData == null) {
            return false;
        }

        if (requestData.isMaxRequestValue()) {
            return false;
        }
        requestData.registerRequest();
        return true;
    }

    private class RequestTimeData {
        private volatile long lastUpdate;
        private final String source;

        private final AtomicInteger siteToSiteCounter = new AtomicInteger(0);

        public RequestTimeData(String source) {
            this.source = Objects.requireNonNull(source, "Source can't be null.");
            logger.debug("Create new request counter for source: {}.", source);
            registerRequest();
        }

        public void registerRequest() {
            siteToSiteCounter.incrementAndGet();
            lastUpdate = System.currentTimeMillis();
        }

        public boolean isMaxRequestValue() {
            return siteToSiteCounter.get() >= guardValue;
        }

        public long getLastUpdate() {
            return lastUpdate;
        }

        public String getSource() {
            return source;
        }
    }
}
