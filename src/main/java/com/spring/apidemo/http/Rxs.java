package com.spring.apidemo.http;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class Rxs {

    /**
     * ？？？
     */
    public static <T> Function<T, Mono<? extends BR<T>>> baseF() {
        return new Function<T, Mono<? extends BR<T>>>() {
            @Override
            public Mono<? extends BR<T>> apply(T t) {
                return Mono.just(BR.create(t));
            }
        };
    }

    /**
     * ？？？
     */
    public static <T> Function<T, Mono<? extends DR<T>>> listF() {
        return new Function<T, Mono<? extends DR<T>>>() {
            @Override
            public Mono<? extends DR<T>> apply(T t) {
                return Mono.just(DR.create(t));
            }
        };
    }

}
