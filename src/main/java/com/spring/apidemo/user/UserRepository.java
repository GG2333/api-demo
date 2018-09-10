package com.spring.apidemo.user;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import com.spring.apidemo.http.Rxs;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Repository
public class UserRepository {

    private final List<User> objects = new ArrayList<>();

    public Mono<BR<List<User>>> getUsers() {
        return Mono.just(objects)
                .flatMap(Rxs.baseF())
                .onErrorResume(throwable -> true, e -> Mono.just(BR.error()));
    }

    public Mono<BR<DR<List<User>>>> getUsers2() {
        return Mono.just(objects)
                .flatMap(Rxs.listF())
                .flatMap(Rxs.baseF())
                .onErrorResume(throwable -> true, e -> Mono.just(BR.error()));
    }

    public Mono<BR<User>> getUserById(Long id) {
        Optional<User> optionalUser = objects.stream()
                .filter(user -> user.id.equals(id))
                .findFirst();

        return Mono.justOrEmpty(optionalUser)
                .flatMap(Rxs.baseF())
                .switchIfEmpty(Mono.just(BR.success()))
                .onErrorResume(throwable -> true, e -> Mono.just(BR.error()));
    }

    public Mono<BR> addUser(User aUser) {
        boolean f = objects.stream()
                .anyMatch(user -> aUser.id.equals(user.id));

        if (!f) {
            objects.add(aUser);
            return Mono.just(BR.success());
        }

        return Mono.just(BR.error());
    }

    public Mono<BR> delUser(User aUser) {
        boolean f = objects.stream()
                .anyMatch(user -> aUser.id.equals(user.id));

        if (f) {
            objects.remove(aUser);
            return Mono.just(BR.success());
        }

        return Mono.just(BR.error());
    }

}
