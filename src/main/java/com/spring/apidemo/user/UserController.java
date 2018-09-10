package com.spring.apidemo.user;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/all")
    @ApiIgnore
    public Mono<BR<List<User>>> users() {
        return repository.getUsers();
    }

    @GetMapping(path = "/all2")
    @ApiIgnore
    public Mono<BR<DR<List<User>>>> users2() {
        return repository.getUsers2();
    }

    @GetMapping(path = "/user")
    @ApiIgnore
    public Mono<BR<User>> getUser(@RequestParam("id") Long id) {
        return repository.getUserById(id);
    }

    @GetMapping(path = "/user/{id}")
    @ApiIgnore
    public Mono<BR<User>> getUserById(@PathVariable("id") Long id) {
        return repository.getUserById(id);
    }

    @PostMapping(path = "/add")
    @ApiIgnore
    public Mono<BR> addUser(@RequestBody User user) {
        return repository.addUser(user);
    }

}
