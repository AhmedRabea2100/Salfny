package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @GetMapping("/profile")
    public ResponseEntity<User> profile(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null && !token.equals("null") && authHandler.validateToken(token)) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(repo.getProfile(authHandler.getEmail()));
        } else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(null);
        }
    }


    @RequestMapping("/profile")
    public String createAccount(@RequestBody User u) {

        User user = repo.getProfile(u.getEmail());
        user.setAddress(u.getAddress());
        user.setPhoneNumber(u.getPhoneNumber());

        // try to store to database
        try {
            repo.save(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Saved";
    }
}
