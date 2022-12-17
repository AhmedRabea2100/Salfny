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
public class StarredController {

    @Autowired
    private PostRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @CrossOrigin
    @GetMapping("/starred")
    public List<Post> starred(@CookieValue(name = "token", required = false) String token) {
        if (token != null && authHandler.validateToken(token)) {
            // TODO: handle token
            return repo.showStarredPosts(authHandler.getEmail());
        } else {
            // TODO: redirect to homepage in front
            return repo.showStarredPosts("e");
            // return null;
        }
    }
}

