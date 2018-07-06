package com.spring.apidemo.mzitu;

import com.spring.apidemo.http.BRMapper;
import com.spring.apidemo.data.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/mzitu")
public class MzituController {

    @Autowired
    private MzituRepository repository;

    @GetMapping(path = "/news")
    public Mono<BR<List<MzituImage>>> news(@RequestParam("page") int page,
                                           @RequestParam("count") int count) {
        return repository.news(page, count)
                .flatMap(new BRMapper<>())
                .switchIfEmpty(Mono.just(BR.empty()))
                .onErrorReturn(BR.error());

    }

    @GetMapping(path = "/news2")
    public Mono<BR<List<MzituImage>>> news2(@RequestParam("page") int page,
                                           @RequestParam("count") int count) {
        return repository.news(page, count)
                .flatMap(new BRMapper<>())
                .switchIfEmpty(Mono.just(BR.empty()));
                //.onErrorReturn(BR.error());//可能的错误

    }

}
