package com.spring.apidemo.gg;

import com.spring.apidemo.data.BR;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/exception")
public class ExceptionController {

    @GetMapping(path = "/get")
    public Mono<BR<String>> get() {
        return Mono.just(BR.success());
    }

    @GetMapping(path = "/get/params")
    public Mono<BR<String>> get(@RequestParam("phone") String phone) {
        return Mono.just(BR.success());
    }

    @GetMapping(path = "/path/{path}")
    public Mono<BR<String>> get3(@PathVariable("path") String path) {
        return Mono.just(BR.success());
    }

    @PostMapping(path = "/post")
    public Mono<BR<String>> post() {
        return Mono.just(BR.success());
    }

}
