package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import com.spring.apidemo.http.Rxs;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private void running() {
        Mono<List<ModelEntity>> mono =
        webClient.get()
                .uri(newsUri, 10, 1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ModelEntity>>() {})
                .onErrorResume(throwable -> true, e -> Mono.empty());

//        log.info("start thread:[{}]",Thread.currentThread().getName());
//        List<ModelEntity> entities = mono.block();
//        log.info("end thread:[{}]",Thread.currentThread().getName());

        log.info("start thread:[{}]",Thread.currentThread().getName());
        Optional<List<ModelEntity>> entities = mono.blockOptional();
        if (entities.isPresent()) {
            log.info("true thread:[{}]",Thread.currentThread().getName());
        } else {
            log.info("false thread:[{}]",Thread.currentThread().getName());
        }
        log.info("end thread:[{}]",Thread.currentThread().getName());

    }


    /**
     *
     * ClientResponse has erroneous status code: 400 Bad Request
     *
     */
    private void run() {
        webClient.get()
                .uri(newsUri, 10, 1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ModelEntity>>() {})
//                .onErrorResume(throwable -> true, e -> Mono.empty())
//                .switchIfEmpty(Mono.just(new ArrayList<>()))

//                .subscribe(new BaseSubscriber<List<ModelEntity>>() {
//                    @Override
//                    protected void hookOnSubscribe(Subscription subscription) {
//                        super.hookOnSubscribe(subscription);
//                        log.info("onSubscribe");
//                        request(1);
//                    }
//
//                    @Override
//                    protected void hookOnNext(List<ModelEntity> value) {
//                        super.hookOnNext(value);
//                        log.info("onNext");
//                    }
//
//                    @Override
//                    protected void hookOnError(Throwable throwable) {
//                        super.hookOnError(throwable);
//                        log.info("onError");
//                    }
//
//                    @Override
//                    protected void hookOnComplete() {
//                        super.hookOnComplete();
//                        log.info("onComplete");
//                    }
//                });

//                .subscribe(new Consumer<List<ModelEntity>>() {
//                    @Override
//                    public void accept(List<ModelEntity> mzituImages) {
//                        log.info(mzituImages.toString());
//                        log.info("next thread:[{}]", Thread.currentThread().getName());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        log.info(throwable.getMessage());
//                        log.info("error thread:[{}]", Thread.currentThread().getName());
//                    }
//                }, new Runnable() {
//                    @Override
//                    public void run() {
//                        log.info("complete thread:[{}]", Thread.currentThread().getName());
//                    }
//                }, new Consumer<Subscription>() {
//                    @Override
//                    public void accept(Subscription subscription) {
//                        log.info("subscribe thread:[{}]", Thread.currentThread().getName());
//                    }
//                });

//                .publishOn(Schedulers.single())
//                .subscribeOn(Schedulers.immediate())
//                .subscribe(new Consumer<List<ModelEntity>>() {
//                    @Override
//                    public void accept(List<ModelEntity> modelEntities) {
//                        log.info("next thread:[{}]", Thread.currentThread().getName());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        log.info("error thread:[{}]", Thread.currentThread().getName());
//                    }
//                });

                .flatMap(Rxs.listF())
                .flatMap(Rxs.baseF())
                .subscribe(new Consumer<BR<DR<List<ModelEntity>>>>() {
                    @Override
                    public void accept(BR<DR<List<ModelEntity>>> drbr) {
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
