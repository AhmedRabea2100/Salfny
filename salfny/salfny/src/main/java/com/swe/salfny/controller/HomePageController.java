package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    private PostRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @CrossOrigin
    @GetMapping("/home")
    public ResponseEntity<List<Post>> homePage(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token!=null && !token.equals("null") && authHandler.validateToken(token)) {

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(repo.showPreferredPosts(authHandler.getEmail()));
        } else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(repo.showRecentPosts(0, 3));
        }
    }
}
