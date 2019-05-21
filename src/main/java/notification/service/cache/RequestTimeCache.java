package notification.service.cache;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.cache.LoadingCache;

import static notification.service.utils.cache.CacheUtils.buildLoadingCache;

public class RequestTimeCache {
    private final LoadingCache<String, RequestTimeData> ipToRequestData =
            buildLoadingCache(10, 10, this::getRequestTimeDataByIp);

    private final long guardValue;

    public RequestTimeCache(long guardValue) {
        this.guardValue = guardValue;
    }

    public boolean isEnabledRequest(String sourceIp) {
        RequestTimeData requestData = ipToRequestData.getUnchecked(sourceIp);

        if (requestData.isMaxRequestValue()) {
            return false;
        }
        requestData.registerRequest();
        return true;
    }

    private RequestTimeData getRequestTimeDataByIp(String sourceIp) {
        return new RequestTimeData(sourceIp);
    }

    private class RequestTimeData {
        private volatile long lastUpdate;
        private final String ipAddress;

        private final AtomicInteger siteToSiteCounter = new AtomicInteger(0);

        public RequestTimeData(String ipAddress) {
            this.ipAddress = Objects.requireNonNull(ipAddress, "Ip address can't be null.");
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

        public String getIpAddress() {
            return ipAddress;
        }
    }
}
