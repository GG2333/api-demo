package com.spring.apidemo.user;

import com.spring.apidemo.data.BR;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class UserRepository {

    private final List<User> objects = new ArrayList<>();

    public Mono<List<User>> getUsers() {
        return Mono.justOrEmpty(objects);
    }

    public Mono<User> getUserById(Long id) {
        return Mono.justOrEmpty(objects
                .stream()
                .filter(user -> user.id.equals(id))
                .findFirst());
    }

    public Mono<BR> addUser(User aUser) {
        boolean f = objects.stream()
                .anyMatch(user -> aUser.id.equals(user.id));

        try {
            if (!f) {
                objects.add(aUser);
                return Mono.just(BR.success());
            } else {
                return Mono.just(BR.error());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(BR.error());
    }

    public Mono<BR> delUser(User aUser) {
        boolean f = objects.stream()
                .anyMatch(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        return aUser.id.equals(user.id);
                    }
                });

        if (!f)
            objects.add(aUser);
        return Mono.just(BR.success());
    }

}
