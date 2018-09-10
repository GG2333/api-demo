package com.spring.apidemo.mzitu;

import javax.persistence.*;

@Entity
@Table(name = "Image")
public class ImageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer id;

    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "src")
    public String src;

    public ImageEntity() {
    }

    public ImageEntity(Integer listId, String src) {
        this.listId = listId;
        this.src = src;
    }
}
