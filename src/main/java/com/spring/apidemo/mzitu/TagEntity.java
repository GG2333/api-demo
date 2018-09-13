package com.spring.apidemo.mzitu;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Tag")
public class TagEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer id;

    @Column(name = "tag_id")
    @JsonProperty(value = "tagid")
    public String tagId;

    @Column(name = "title")
    public String title;

    @Column(name = "cover")
    public String cover;

    @Override
    public String toString() {
        return "TagEntity{" +
                "tagId='" + tagId + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }

}
