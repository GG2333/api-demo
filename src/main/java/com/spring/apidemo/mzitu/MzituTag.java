package com.spring.apidemo.mzitu;

public class MzituTag {

    public String cover;
    public String tagid;
    public String title;

    @Override
    public String toString() {
        return "MzituTag{" +
                "tagid='" + tagid + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
