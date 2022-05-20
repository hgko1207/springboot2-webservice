package com.hgko.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRqquestDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateRqquestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
