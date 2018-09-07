package com.spring.apidemo.mzitu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class GirlsConfig {

    private static final Logger log = LoggerFactory.getLogger(GirlsConfig.class);

    @Value("${image.base-url}")
    private String baseUrl;

    @Bean
    public WebClient newWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                    log.info("Request: " + clientRequest.url());
                    return Mono.just(clientRequest);
                }))
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    log.info("Response: " + clientResponse.statusCode().toString());
                    return Mono.just(clientResponse);
                }))
                .build();
    }

}
