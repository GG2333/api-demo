package com.spring.apidemo.http;

import com.spring.apidemo.data.BR;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class BRMapper<T> implements Function<T, Mono<? extends BR<T>>> {

    @Override
    public Mono<? extends BR<T>> apply(T t) {
        return Mono.just(BR.create(t));
    }

}
