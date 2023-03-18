package com.springboot.blog.payload;

import lombok.Data;

@Data
public class postdto {
    private long id;
    private String title;
    private String description;
    private String content;
}
