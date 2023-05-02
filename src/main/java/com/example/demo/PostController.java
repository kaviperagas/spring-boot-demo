package com.example.demo;

import java.io.IOException;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() throws IOException {
        return postService.getAllPosts();
    }

    @GetMapping("/longest-title")
    public Post getPostWithLongestTitle() throws IOException {
        return postService.getPostWithLongestTitle();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getAllPostsFormatted() throws IOException {
        List<Post> posts = postService.getAllPosts();
        List<Map<String, Object>> formattedPosts = new ArrayList<>();
        for (Post post : posts) {
            Map<String, Object> formattedPost = new HashMap<>();
            formattedPost.put("id", post.getId());
            formattedPost.put("userId", post.getUserId());
            formattedPost.put("title", post.getTitle());
            formattedPost.put("body", post.getBody());
            formattedPost.put("titleLength", post.getTitle().length());
            formattedPosts.add(formattedPost);
        }
        return formattedPosts;
    }
}

