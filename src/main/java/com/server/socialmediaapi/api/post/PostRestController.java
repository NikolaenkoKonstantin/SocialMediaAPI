package com.server.socialmediaapi.api.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostRestController {

    //С пагинацией
    @GetMapping
    public ResponseEntity<HttpStatus> getPosts(){
        return null;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createPost(@RequestParam("image") MultipartFile image){
        return null;
    }


    @PatchMapping
    public ResponseEntity<HttpStatus> updatePost(){
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") int id){
        return null;
    }
}
