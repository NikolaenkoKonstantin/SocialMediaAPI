package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.post.dto.PostCreateRequest;
import com.server.socialmediaapi.api.post.dto.PostUpdateRequest;
import com.server.socialmediaapi.models.Post;
import com.server.socialmediaapi.repositories.PostRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;


    public Page<Post> getPosts(int subscriber, int page, int size){
        return postRepo.search(
                userRepo.getOrThrow(subscriber),
                PageRequest.of(page, size)
        );
    }


    @Transactional
    public Post updatePost(int id, PostUpdateRequest dto){
        return postRepo.save(editPost(id, dto));
    }


    private Post editPost(int id, PostUpdateRequest dto) {
        Post post = postRepo.getOrThrow(id);

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }


    @Transactional
    public void deletePost(int id){
        postRepo.deleteById(id);
    }


    @Transactional
    public Post addPost(PostCreateRequest dto){
        return postRepo.save(createPost(dto));
    }


    private Post createPost(PostCreateRequest dto) {
        return new Post(
                userRepo.getOrThrow(dto.getOwner()),
                dto.getTitle(),
                dto.getContent(),
                LocalDateTime.now()
        );
    }


}
