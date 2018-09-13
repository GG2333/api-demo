package com.spring.apidemo.mzitu;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(value = "img_num")
    public int number;

    @Column(name = "thumb_src")
    @JsonProperty(value = "thumb_src")
    public String src;

    @Column(name = "thumb_src_min")
    @JsonProperty(value = "thumb_src_min")
    public String minSrc;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "tag_id", insertable=false, updatable=false)
    public TagEntity tagEntity;

    @Override
    public String toString() {
        return "ModelEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
