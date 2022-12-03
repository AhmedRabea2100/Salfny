package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.post.Post;
import com.swe.salfny.post.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private PostRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @CrossOrigin
    @GetMapping("/")
    public List<Post> homePage(@CookieValue(name="token",required = false) String token) {

        if(token!=null && authHandler.validateToken(token)){
            return new ArrayList<Post>();//Preferences posts
        }else{
            return repo.showRecentPosts();
        }
    }
}
