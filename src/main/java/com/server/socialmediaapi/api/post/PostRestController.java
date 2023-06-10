package com.server.socialmediaapi.api.post;

import com.server.socialmediaapi.api.post.dto.PostConverter;
import com.server.socialmediaapi.api.post.dto.PostCreateRequest;
import com.server.socialmediaapi.api.post.dto.PostResponse;
import com.server.socialmediaapi.api.post.dto.PostUpdateRequest;
import com.server.socialmediaapi.model.Post;
import com.server.socialmediaapi.services.PostService;
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
    private final PostConverter converter;
    private final PostService postService;


    @GetMapping("/{id}")
    public ResponseEntity<Page<PostResponse>> getPosts(@PathVariable("id") int id,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<PostResponse> posts = postService.getPosts(id, page, size).map(converter::convertTOPostResponse);
        return ResponseEntity.ok(posts);
    }


    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostCreateRequest dto){
        Post post = postService.addPost(dto);
        return ResponseEntity.ok(converter.convertTOPostResponse(post));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("id") int id,
                                                 @RequestBody @Valid PostUpdateRequest dto){
        Post post = postService.updatePost(id, dto);
        return ResponseEntity.ok(converter.convertTOPostResponse(post));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") int id){
        postService.deletePost(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
