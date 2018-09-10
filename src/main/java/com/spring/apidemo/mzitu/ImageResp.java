package com.spring.apidemo.mzitu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ImageResp {

    public String content;

    public List<String> images() {
        if (Objects.nonNull(content)) {
            String[] images = content.split(",");
            return Arrays.stream(images)
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String s) {
                            return s.replace("\"", "");
                        }
                    }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
