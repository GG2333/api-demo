package com.spring.apidemo.mzitu;

import com.spring.apidemo.data.BR;
import com.spring.apidemo.data.DR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(path = "/pull")
public class PullController {

    @Autowired
    private PullRepository repository;

    @GetMapping(path = "/tags")
    @ApiIgnore
    public Mono<BR<DR<List<TagEntity>>>> tags(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "count", required = false, defaultValue = "10") int count) {
        return repository.tags(page, count);
    }

    @GetMapping(path = "/lists")
    @ApiIgnore
    public Mono<BR<DR<List<ModelEntity>>>> lists(
            @RequestParam("id") int id,
            @RequestParam("page") int page,
            @RequestParam("count") int count) {
        return repository.getListsByTagId(id, page, count);
    }

    @GetMapping(path = "/images")
    @ApiIgnore
    public Mono<BR<DR<List<ImageEntity>>>> images(
            @RequestParam("id") int id,
            @RequestParam("page") int page,
            @RequestParam("count") int count) {
        return repository.getImagesByImageId(id, page, count);
    }

    @GetMapping(path = "/another-lists")
    @ApiIgnore
    public Mono<BR<DR<List<ModelEntity>>>> anotherLists(
            @RequestParam("id") int id,
            @RequestParam("page") int page,
            @RequestParam("count") int count) {
        return repository.getAnotherLists(id, page, count);
    }

    @GetMapping(path = "/all-lists")
    @ApiIgnore
    public Mono<BR<DR<List<ModelEntity>>>> allLists(
            @RequestParam("page") int page,
            @RequestParam("count") int count) {
        return repository.getAllSortLists(page, count);
    }

}
