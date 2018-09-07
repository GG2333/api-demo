package com.spring.apidemo.laihuola;

import com.spring.apidemo.laihuola.data.BaseR;
import com.spring.apidemo.laihuola.data.DataR;
import com.spring.apidemo.laihuola.resp.GoodsResp;
import com.spring.apidemo.laihuola.resp.LoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/laihuola")
public class LaihuolaController {

    @Autowired
    private LaihuolaService service;

    @GetMapping(path = "/login")
    public Mono<BaseR<LoginResp>> login(
            @RequestParam("phone") String phone,
            @RequestParam("captcha") String captcha) {
        return service.login(phone, captcha);
    }

    @GetMapping(path = "/captcha")
    public Mono<BaseR> captcha(@RequestParam("phone") String phone) {
        return service.captcha(phone);
    }

    @GetMapping(path = "/goods/all")
    public Mono<BaseR<DataR<List<GoodsResp>>>> goods(
            @RequestHeader(value = "Token") String token,
            @RequestParam(value = "place1", required = false) String place1,
            @RequestParam(value = "place2", required = false) String place2,
            @RequestParam(value = "container") int container,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "count") int count) {

        return service.goods(token, place1, place2, container, page, count);
    }


}
