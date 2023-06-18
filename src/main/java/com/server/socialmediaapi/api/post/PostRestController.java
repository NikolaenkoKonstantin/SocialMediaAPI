package com.server.socialmediaapi.api.post;

import com.server.socialmediaapi.api.post.dto.PostConverter;
import com.server.socialmediaapi.api.post.dto.PostCreateRequest;
import com.server.socialmediaapi.api.post.dto.PostResponse;
import com.server.socialmediaapi.api.post.dto.PostUpdateRequest;
import com.server.socialmediaapi.models.Post;
import com.server.socialmediaapi.services.PostService;
import com.server.socialmediaapi.utils.HibernateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostRestController {
    private final HibernateValidator hibernateValidator;
    private final PostConverter converter;
    private final PostService postService;



    @GetMapping("/{id}")
    public Page<PostResponse> getPosts(@PathVariable("id") int id,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        return postService.getPosts(id, page, size).map(converter::convertTOPostResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody @Valid PostCreateRequest dto,
                                                   BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        return converter.convertTOPostResponse(postService.addPost(dto));
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostResponse updatePost(@PathVariable("id") int id,
                                   @RequestBody @Valid PostUpdateRequest dto,
                                   BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        return converter.convertTOPostResponse(postService.updatePost(id, dto));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id){
        postService.deletePost(id);
    }
}
