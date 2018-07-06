package com.spring.apidemo.mzitu;

import com.spring.apidemo.http.EmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class MzituRepository {

    private static final Logger log = LoggerFactory.getLogger(MzituRepository.class);

    private static final String baseUrl = "http://adr.meizitu.net";
    private static final String newsUri = "/wp-json/wp/v2/posts?per_page={count}&page={page}";

    private WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
//                .filter(ExchangeFilterFunctions.basicAuthentication("username", "password"))
//                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
//                    log.info("Request: " + clientRequest.url());
//                    return Mono.just(clientRequest);
//                }))
//                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//                    log.info("Response: " + clientResponse.statusCode());
//                    return Mono.just(clientResponse);
//                }))
            .build();

    public Mono<List<MzituImage>> news(int page, int count) {
        return webClient.get()
                .uri(newsUri, count, page)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new EmptyException("empty")))
                .bodyToMono(new ParameterizedTypeReference<List<MzituImage>>() {
                })
                .onErrorResume(EmptyException.class, e -> Mono.empty());
    }

}
