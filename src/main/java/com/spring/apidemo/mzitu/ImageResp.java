package com.spring.apidemo.mzitu;

import java.util.*;

public class ImageResp {

    public String content;

    public List<String> images() {
        if (Objects.nonNull(content)) {
            String[] images = content.split(",");
            return Arrays.asList(images);
        }
        return new ArrayList<>();
    }

}
