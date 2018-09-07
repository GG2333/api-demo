package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.http.Rxs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class MzituRepository {

    private static final Logger log = LoggerFactory.getLogger(MzituRepository.class);

    private static final String newsUrl = "/wp-json/wp/v2/posts?per_page={count}&page={page}";
    private static final String tagsUrl = "/json/x.json";
    private static final String imagesUrl = "/wp-json/wp/v2/posts?tags={tags}&page={page}";
    private static final String imageUrl = "/wp-json/wp/v2/i?id={id}";

    @Autowired
    private WebClient webClient;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Deprecated
    public Mono<List<ModelEntity>> news(String page, String count) {
        Mono<List<ModelEntity>> listMono = webClient.get()
                .uri(newsUrl, count, page)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ModelEntity>>() {
                })
                .onErrorResume(throwable -> true, e -> Mono.just(new ArrayList<>()));

        listMono.subscribe(new Consumer<List<ModelEntity>>() {
            @Override
            public void accept(List<ModelEntity> imageEntities) {
                imageEntities.forEach(new Consumer<ModelEntity>() {
                    @Override
                    public void accept(ModelEntity imageEntity) {
                        modelRepository.save(imageEntity);
                    }
                });
            }
        });

        return listMono;
    }

    @Deprecated
    public Mono<BR<List<ModelEntity>>> news2(String page, String count) {
        return webClient.get()
                .uri(newsUrl, count, page)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ModelEntity>>() {
                })
                .flatMap(Rxs.baseF())
                .onErrorResume(throwable -> true, e -> Mono.just(BR.error()));
    }

    public Mono<List<List<TagEntity>>> tags() {
        Mono<List<List<TagEntity>>> listMono = webClient.get()
                .uri(tagsUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<List<TagEntity>>>() {
                })
                .onErrorResume(throwable -> true, e -> Mono.just(new ArrayList<>()));

        listMono.subscribe(new Consumer<List<List<TagEntity>>>() {
            @Override
            public void accept(List<List<TagEntity>> lists) {
                lists.forEach(new Consumer<List<TagEntity>>() {
                    @Override
                    public void accept(List<TagEntity> tagEntities) {
                        tagEntities.forEach(new Consumer<TagEntity>() {
                            @Override
                            public void accept(TagEntity tagEntity) {
                                tagRepository.save(tagEntity);
                            }
                        });
                    }
                });
            }
        });

        return listMono;
    }

    public Mono<List<ModelEntity>> images(String id, String page) {
        Mono<List<ModelEntity>> listMono = webClient.get()
                .uri(imagesUrl, id, page)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ModelEntity>>() {
                })
                .onErrorResume(throwable -> true, e -> Mono.just(new ArrayList<>()));

        listMono.subscribe(new Consumer<List<ModelEntity>>() {
            @Override
            public void accept(List<ModelEntity> imageEntities) {
                imageEntities.forEach(new Consumer<ModelEntity>() {
                    @Override
                    public void accept(ModelEntity modelEntity) {
                        modelEntity.tag_id = Integer.valueOf(id);
                        modelRepository.save(modelEntity);
                    }
                });
            }
        });

        return listMono;
    }

    public Mono<ImageResp> image(String id) {
        Mono<ImageResp> imageRespMono = webClient.get()
                .uri(imageUrl, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ImageResp.class)
                .onErrorResume(throwable -> true, e -> Mono.just(new ImageResp()));

        imageRespMono.subscribe(new Consumer<ImageResp>() {
            @Override
            public void accept(ImageResp imageResp) {
                imageResp.images().forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        ImageEntity imageEntity = new ImageEntity(Integer.valueOf(id), s);
                        imageRepository.save(imageEntity);
                    }
                });
            }
        });

        return imageRespMono;
    }

}
