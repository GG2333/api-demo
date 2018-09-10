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
    public Integer tagId;

    @Column(name = "title")
    public String title;

    @Column(name = "img_num")
    public int number;

    @Column(name = "thumb_src")
    public String src;

    @Column(name = "thumb_src_min")
    public String minSrc;


//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "tag_id", insertable=false, updatable=false)
//    public TagEntity tagEntity;

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
