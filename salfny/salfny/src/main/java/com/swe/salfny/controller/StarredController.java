package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StarredController {

    @Autowired
    private PostRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @CrossOrigin
    @GetMapping("/starred")
    public ResponseEntity<List<Post>> starred(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (authHandler.validateToken(token)) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(repo.showStarredPosts(authHandler.getEmail()));
        } else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(null);
        }
    }
}
