package com.server.socialmediaapi.api.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private int id;

    private int owner;

    private String title;

    private String content;

    private LocalDateTime dateOfCreation;
}
