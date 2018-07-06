package com.spring.apidemo.mzitu;

public class MzituImage {

    public String thumb_src_min;
    public int id;
    public String title;
    public int img_num;
    public String thumb_src;

    @Override
    public String toString() {
        return "MzituImage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
