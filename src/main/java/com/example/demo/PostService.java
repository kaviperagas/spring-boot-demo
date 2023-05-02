package com.example.demo;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class PostService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PostService() {
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Post> getAllPosts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/posts")
                .build();
        Response response = httpClient.newCall(request).execute();
        String json = response.body().string();
        return objectMapper.readValue(json, new TypeReference<List<Post>>(){});
    }

    public Post getPostWithLongestTitle() throws IOException {
        List<Post> posts = getAllPosts();
        return posts.stream()
                .max(Comparator.comparingInt(p -> p.getTitle().length()))
                .orElseThrow(() -> new RuntimeException("No posts found"));
    }
}

