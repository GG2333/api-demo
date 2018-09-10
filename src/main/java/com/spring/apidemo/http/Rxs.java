package com.spring.apidemo.http;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.List;
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

    /**
     * 分页
     */
    public static <T> Function<Page<T>, Mono<? extends DR<List<T>>>> pageF() {
        return new Function<Page<T>, Mono<? extends DR<List<T>>>>() {
            @Override
            public Mono<? extends DR<List<T>>> apply(Page<T> ts) {
                DR<List<T>> listDR = DR.create(ts.getContent());
                listDR.number = ts.getNumber();
                listDR.numberOfElements = ts.getNumberOfElements();
                listDR.size = ts.getSize();
                listDR.isLast = ts.isLast();
                listDR.totalPages = ts.getTotalPages();
                listDR.totalElements = ts.getTotalElements();
                return Mono.just(listDR);
            }
        };
    }

}
