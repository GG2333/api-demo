package com.spring.apidemo.live;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BabyDetail")
public class DetailEntity {


    /**
     * img : http://xiaobabys.oss-cn-shanghai.aliyuncs.com/public/attachment/201810/1113805/201810230636142547.png
     * play_url : http://h16.jiayongfeng.wang/live/258550_9b045b1dc3a727340a31.flv?auth_key=1540404369-0-0-edd2639b92e0e95186d85b3bc490d2bc
     * title : 猪猪欧尼c
     */

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "title")
    @JsonProperty(value = "title")
    public String title;

    @Column(name = "image")
    @JsonProperty(value = "img")
    public String img;

    @Column(name = "url")
    @JsonProperty(value = "play_url")
    public String play_url;


}
