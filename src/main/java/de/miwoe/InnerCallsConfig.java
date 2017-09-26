package de.miwoe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by mwoelm on 18.09.17.
 */
@Configuration
public class InnerCallsConfig {

    public static final ThreadLocal<UUID> correlationId = new ThreadLocal<UUID>();
    public static final ThreadLocal<String> service = new ThreadLocal<>();

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                HttpRequestWrapper requestWrapper = new HttpRequestWrapper(httpRequest);
                if (correlationId != null && correlationId.get() != null) {
                    requestWrapper.getHeaders().set("correlationId", correlationId.get().toString());
                    requestWrapper.getHeaders().set("serviceName", service.get());
                }else {
                    requestWrapper.getHeaders().set("correlationId", "notset");

                }
                return clientHttpRequestExecution.execute(requestWrapper, body);
            }
        });
        return restTemplate;
    }
}
