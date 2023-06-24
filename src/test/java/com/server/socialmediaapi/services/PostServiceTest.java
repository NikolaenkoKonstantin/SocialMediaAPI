package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.post.dto.PostCreateRequest;
import com.server.socialmediaapi.api.post.dto.PostUpdateRequest;
import com.server.socialmediaapi.models.Post;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.PostRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceTest {
    @MockBean private PostRepository postRepository;
    @MockBean private UserRepository userRepository;

    @Autowired private PostService postService;

    private final User firstUser = new User(1, "name", "email", "password");
    private final User secondUser = new User(2, "name", "email", "password");


    @Test
    void addPost() {
        Post post = new Post(1, firstUser, "Post", "content", LocalDateTime.now());

        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post returnPost = postService.addPost(new PostCreateRequest(1, "Post", "content"));

        assertAll(
                () -> assertEquals(post.getId(), returnPost.getId()),
                () -> assertEquals(post.getOwner(), returnPost.getOwner()),
                () -> assertEquals(post.getTitle(), returnPost.getTitle()),
                () -> assertEquals(post.getContent(), returnPost.getContent()),
                () -> assertEquals(post.getDateOfCreation(), returnPost.getDateOfCreation())
        );
    }

    @Test
    void updatePost() {
        Post post = new Post(1, firstUser, "Update post", "content", LocalDateTime.now());

        when(postRepository.getOrThrow(1)).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post returnPost = postService.updatePost(1, new PostUpdateRequest("Update post", "content"));

        assertAll(
                () -> assertEquals(post.getId(), returnPost.getId()),
                () -> assertEquals(post.getOwner(), returnPost.getOwner()),
                () -> assertEquals(post.getTitle(), returnPost.getTitle()),
                () -> assertEquals(post.getContent(), returnPost.getContent()),
                () -> assertEquals(post.getDateOfCreation(), returnPost.getDateOfCreation())
        );
    }

    @Test
    void deletePost() {
        postService.deletePost(1);
        verify(postRepository).deleteById(1);
    }

    @Test
    void getPosts() {
        Page<Post> posts = new PageImpl<>(List.of(
                new Post(1, firstUser, "title one", "content one", LocalDateTime.now()),
                new Post(2, secondUser, "title two", "content two", LocalDateTime.now())));

        when(postRepository.search(any(), any(Pageable.class))).thenReturn(posts);
        Page<Post> postPage = postService.getPosts(3, 0, 2);

        assertEquals(2, postPage.getTotalElements());
    }
}