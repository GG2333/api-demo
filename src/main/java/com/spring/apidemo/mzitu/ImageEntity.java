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
    private Integer list_id;

    @Column(name = "src")
    public String src;


    public ImageEntity(Integer list_id, String src) {
        this.list_id = list_id;
        this.src = src;
    }
}
