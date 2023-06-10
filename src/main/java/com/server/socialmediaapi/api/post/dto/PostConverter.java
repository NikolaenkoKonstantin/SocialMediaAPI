package com.server.socialmediaapi.api.post.dto;

import com.server.socialmediaapi.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostResponse convertTOPostResponse(Post post){
        return new PostResponse(
                post.getId(),
                post.getOwner().getId(),
                post.getTitle(),
                post.getContent(),
                post.getDateOfCreation()
        );
    }
}
