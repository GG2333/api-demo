package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.RR;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(path = "/mzitu")
@Api(tags = "妹子图")
public class MzituController {

    @Autowired
    private MzituRepository repository;

    @GetMapping(path = "/news")
    @ApiIgnore
    public Mono<List<ModelEntity>> news(
            @RequestParam("page") String page,
            @RequestParam("count") String count) {
        return repository.news(page, count);
    }

    @GetMapping(path = "/news2")
    @ApiOperation(value = "最新", notes = "最新妹子图")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "count", required = true, dataType = "string")
    })
    public Mono<BR<List<ModelEntity>>> news2(
            @RequestParam("page") String page,
            @RequestParam("count") String count) {
        return repository.news2(page, count);
    }

    @GetMapping(path = "/tags")
    @ApiIgnore
    public Mono<List<List<TagEntity>>> tags() {
        return repository.tags();
    }

    @GetMapping(path = "/images")
    @ApiIgnore
    public Mono<List<ModelEntity>> images(
            @RequestParam("id") String id,
            @RequestParam("page") String page) {
        return repository.images(id, page);
    }

    @GetMapping(path = "/image")
    @ApiIgnore
    public Mono<RR> image(@RequestParam("id") String id) {
        return repository.image(id);
    }

}
