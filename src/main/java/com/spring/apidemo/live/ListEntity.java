package com.spring.apidemo.live;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BabyList")
public class ListEntity {


    /**
     * number : 100
     * img : http://wx2.sinaimg.cn/mw690/0060lm7Tly1fvjduokhnlj3050050dfv.jpg
     * name : jsonyenulang.txt
     * is_badge : 1
     * title : 夜女郎
     */

    @Id
    @Column(name = "name")
    @JsonProperty(value = "name")
    public String name;

    @Column(name = "title")
    @JsonProperty(value = "title")
    public String title;

    @Column(name = "number")
    @JsonProperty(value = "number")
    public String number;

    @Column(name = "image")
    @JsonProperty(value = "img")
    public String img;


}
