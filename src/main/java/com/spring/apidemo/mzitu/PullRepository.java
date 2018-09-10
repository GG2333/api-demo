package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import com.spring.apidemo.http.Rxs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class PullRepository {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Mono<BR<DR<List<TagEntity>>>> tags(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return Mono.just(tagRepository.findAll(pageable))
                .flatMap(Rxs.pageF())
                .flatMap(Rxs.baseF())
                .onErrorReturn(BR.error());
    }

    public Mono<BR<DR<List<ModelEntity>>>> getListsByTagId(int id, int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return Mono.just(modelRepository.findAllByTagId(pageable, id))
                .flatMap(Rxs.pageF())
                .flatMap(Rxs.baseF())
                .onErrorReturn(BR.error());
    }

    public Mono<BR<DR<List<ImageEntity>>>> getImagesByImageId(int id, int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return Mono.just(imageRepository.findAllByListId(pageable, id))
                .flatMap(Rxs.pageF())
                .flatMap(Rxs.baseF())
                .onErrorReturn(BR.error());
    }


}
