package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    private PostRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @CrossOrigin
    @GetMapping("/home")
    public List<Post> homePage(@CookieValue(name = "token", required = false) String token) {
        if (token != null && authHandler.validateToken(token)) {
            return repo.showPreferredPosts(authHandler.getEmail());
        } else {
            return repo.showRecentPosts(0, 3);
        }
    }
}
