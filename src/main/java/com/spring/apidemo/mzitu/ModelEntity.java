package com.spring.apidemo.mzitu;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Model")
public class ModelEntity {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "tag_id")
    public Integer tag_id;

    @Column(name = "title")
    public String title;

    @Column(name = "img_num")
    public int img_num;

    @Column(name = "thumb_src")
    public String thumb_src;

    @Column(name = "thumb_src_min")
    public String thumb_src_min;

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
