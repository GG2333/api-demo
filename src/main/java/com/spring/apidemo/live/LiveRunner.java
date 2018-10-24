package com.spring.apidemo.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Consumer;

@Component
public class LiveRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LiveRunner.class);

    private static final String baseUrl = "http://t.59card.com";
    private static final String listUrl = "/mobile/live/index";
    private static final String detailUrl = "/mobile/live/anchors";

    private WebClient webClient;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private DetailRepository detailRepository;

    @Override
    public void run(String... args) throws Exception {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
//        running();
//        details("jsonyenulang.txt");
//        details("jsonxiaobaobei.txt");
//        details("jsondaxiaojie.txt");
//        details("jsonxiaoxinggan.txt");

    }

    private void running() {
        webClient.post()
                .uri(listUrl)
                .header("token", "bbbe4c79779ccce0a59f34b532e29faf")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<LiveResp<ListResp<List<ListEntity>>>>() {
                })
                .flatMap(RxLives.baseMapper())
                .flatMap(RxLives.listMapper())
                .subscribe(new Consumer<List<ListEntity>>() {
                    @Override
                    public void accept(List<ListEntity> listEntities) {
                        saveLists(listEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        log.info(throwable.getMessage());
                    }
                });
    }

    private void saveLists(List<ListEntity> listEntities) {
        listEntities.forEach(new Consumer<ListEntity>() {
            @Override
            public void accept(ListEntity listEntity) {
                listRepository.save(listEntity);
            }
        });
    }

    private void details(String name) {
        webClient.post()
                .uri(detailUrl)
                .body(BodyInserters.fromFormData("name", name))
                .header("token", "bbbe4c79779ccce0a59f34b532e29faf")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<LiveResp<ListResp<List<DetailEntity>>>>() {
                })
                .flatMap(RxLives.baseMapper())
                .flatMap(RxLives.listMapper())
                .subscribe(new Consumer<List<DetailEntity>>() {
                    @Override
                    public void accept(List<DetailEntity> detailEntities) {
                        saveDetails(name, detailEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        log.info(throwable.getMessage());
                    }
                });
    }

    private void saveDetails(String name, List<DetailEntity> detailEntities) {
        detailEntities.forEach(new Consumer<DetailEntity>() {
            @Override
            public void accept(DetailEntity detailEntity) {
                detailEntity.name = name;
                detailRepository.save(detailEntity);
            }
        });
    }

}
