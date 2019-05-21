package notification.service.utils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

public final class RestTemplateUtils {
    private final ThreadPoolTaskScheduler taskScheduler;

    public RestTemplateUtils(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = Objects.requireNonNull(taskScheduler,
                "Task scheduler can't be null.");
    }

    public RestTemplate getRestTemplate() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(10);
        connectionManager.setValidateAfterInactivity(10_000);
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClients.custom().setConnectionManager(connectionManager).setConnectionManagerShared(true).build());
        httpRequestFactory.setConnectionRequestTimeout(10_000);
        httpRequestFactory.setConnectTimeout(10_000);
        httpRequestFactory.setReadTimeout(10_000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        taskScheduler.scheduleWithFixedDelay(() -> {
            connectionManager.closeExpiredConnections();
            connectionManager.closeIdleConnections(30, TimeUnit.SECONDS);
        }, 10_000);
        return restTemplate;
    }
}
