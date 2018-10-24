package com.spring.apidemo.live;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import com.spring.apidemo.data.SelfException;
import com.spring.apidemo.http.EmptyException;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class RxLives {

    public static <T> Function<LiveResp<T>, Mono<T>> baseMapper() {
        return new Function<LiveResp<T>, Mono<T>>() {
            @Override
            public Mono<T> apply(LiveResp<T> tLiveResp) {
                if (tLiveResp.code != 0) {
                    return Mono.error(new SelfException(tLiveResp.code, tLiveResp.msg));
                }
                return Mono.just(tLiveResp.data);
            }
        };
    }

    public static <T> Function<ListResp<T>, Mono<T>> listMapper() {
        return new Function<ListResp<T>, Mono<T>>() {
            @Override
            public Mono<T> apply(ListResp<T> tListResp) {
                if (tListResp.lists == null) {
                    return Mono.error(new EmptyException());
                }
                return Mono.just(tListResp.lists);
            }
        };
    }


}
