package com.server.socialmediaapi.api.post.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    @Size(min = 2, max = 100, message = "2 between 100")
    private String title;

    @Size(max = 1000, message = "max = 1000")
    private String content;
}
