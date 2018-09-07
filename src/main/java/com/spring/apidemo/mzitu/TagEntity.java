package com.spring.apidemo.mzitu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tag")
public class TagEntity {

    @Id
    @Column(name = "id")
    public String tagid;

    @Column(name = "title")
    public String title;

    @Column(name = "cover")
    public String cover;

    @Override
    public String toString() {
        return "TagEntity{" +
                "tagid='" + tagid + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
