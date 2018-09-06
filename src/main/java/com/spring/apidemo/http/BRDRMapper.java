package com.spring.apidemo.http;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Deprecated
public class BRDRMapper<T> implements Function<T, Mono<? extends BR<DR<T>>>> {

    @Override
    public Mono<? extends BR<DR<T>>> apply(T t) {
        return Mono.just(BR.create(DR.create(t)));
    }

}
