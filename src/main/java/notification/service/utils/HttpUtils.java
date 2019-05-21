package notification.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static notification.service.utils.JsonUtils.EMPTY_JSON;

public final class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private final RestTemplate restTemplate;

    public HttpUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> jsonGetRequest(String requestPath) {
        return jsonHttpRequest(HttpMethod.GET, requestPath, new HttpHeaders(), EMPTY_JSON);
    }

    public ResponseEntity<String> jsonPostRequest(String requestPath, HttpHeaders headers, Object requestBody) {
        return jsonHttpRequest(HttpMethod.POST, requestPath, headers, requestBody);
    }

    private ResponseEntity<String> jsonHttpRequest(HttpMethod httpMethod, String requestPath,
            HttpHeaders headers, Object requestBody) {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String convertObjectToJson = JsonUtils.convertObjectToJson(requestBody);
        logger.debug("Json after converting: {}.", convertObjectToJson);
        HttpEntity request = new HttpEntity<>(convertObjectToJson, headers);
        try {
            return restTemplate.exchange(requestPath, httpMethod, request, String.class);
        } catch (HttpStatusCodeException ex) {
            logger.error("Request to {} failed with status: {} and message: {}.", requestPath, ex.getStatusCode(),
                    ex.getResponseBodyAsString());
            return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        } catch (RestClientException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
