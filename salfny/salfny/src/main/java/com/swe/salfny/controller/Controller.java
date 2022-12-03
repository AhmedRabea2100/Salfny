package com.swe.salfny.controller;

import com.swe.salfny.post.Post;
import com.swe.salfny.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private PostRepository repo;

    @CrossOrigin
    @GetMapping("/")
    public List<Post> homePage(@RequestBody String token) {
//        if no token
//            return repo.showRecentPosts();
//        else
            return repo.showPreferredPosts(token);

    }
}
