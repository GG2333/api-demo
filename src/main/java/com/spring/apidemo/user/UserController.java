package com.spring.apidemo.user;

import com.spring.apidemo.http.BRDRMapper;
import com.spring.apidemo.http.BRMapper;
import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/all")
    public Mono<BR<List<User>>> users() {
        return repository.getUsers()
                .flatMap(new BRMapper<>())
                .switchIfEmpty(Mono.just(BR.error()));
    }

    @GetMapping(path = "/all2")
    public Mono<BR<DR<List<User>>>> users2() {
        return repository.getUsers()
                .flatMap(new BRDRMapper<>())
                .switchIfEmpty(Mono.just(BR.error()));
    }

    @GetMapping(path = "/user")
    public Mono<BR<User>> getUser(@RequestParam("id") Long id) {
        return repository.getUserById(id)
                .flatMap(new BRMapper<>())
                .switchIfEmpty(Mono.just(BR.error()));
    }

    @GetMapping(path = "/user/{id}")
    public Mono<BR<User>> getUserById(@PathVariable("id") Long id) {
        return repository.getUserById(id)
                .flatMap(new BRMapper<>())
                .switchIfEmpty(Mono.just(BR.error()));
    }

    @PostMapping(path = "/add")
    public Mono<BR> addUser(@RequestBody User user) {
        return repository.addUser(user);
    }

}
