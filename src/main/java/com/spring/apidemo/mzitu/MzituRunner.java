package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import com.spring.apidemo.http.Rxs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class MzituRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MzituRunner.class);

    private static final String baseUrl = "http://adr.meizitu.net";
    private static final String newsUri = "/wp-json/wp/v2/posts?per_page={count}&page={page}";

    private WebClient webClient;

    @Override
    public void run(String... args) throws Exception {
//        webClient = WebClient.builder()
//                .baseUrl(baseUrl)
//                .build();
//        run();
    }

    /**
     *
     * ClientResponse has erroneous status code: 400 Bad Request
     *
     */
    private void run() {
        webClient.get()
                .uri(newsUri, 10, 10000)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MzituImage>>() {})
                .onErrorResume(throwable -> true, e -> Mono.empty())
                .switchIfEmpty(Mono.just(new ArrayList<>()))

//                .subscribe(new Consumer<List<MzituImage>>() {
//                    @Override
//                    public void accept(List<MzituImage> mzituImages) {
//                        log.info(mzituImages.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        log.info(throwable.getMessage());
//                    }
//                });

//                .flatMap(Rxs.baseF())
//                .subscribe(new Consumer<BR<List<MzituImage>>>() {
//                    @Override
//                    public void accept(BR<List<MzituImage>> listBR) {
//                        log.info(listBR.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        log.info(throwable.getMessage());
//                    }
//                });

                .flatMap(Rxs.listF())
                .flatMap(Rxs.baseF())

                // 为什么不能用呢？？？
//                .subscribe(new Subscriber<BR<DR<List<MzituImage>>>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        log.info("onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(BR<DR<List<MzituImage>>> drbr) {
//                        log.info(drbr.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        log.info("onError");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        log.info("onComplete");
//                    }
//                });

                .subscribe(new Consumer<BR<DR<List<MzituImage>>>>() {
                    @Override
                    public void accept(BR<DR<List<MzituImage>>> drbr) {
                        log.info(drbr.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        log.info(throwable.getMessage());
                    }
                });

    }

}
