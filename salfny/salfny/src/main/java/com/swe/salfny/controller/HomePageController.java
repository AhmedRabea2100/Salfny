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

import java.util.List;

@RestController
@CrossOrigin
public class HomePageController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthHandler authHandler;

    @GetMapping("/home")
    public ResponseEntity<List<Post>> homePage(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token!=null && !token.equals("null") && authHandler.validateToken(token)) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(postRepo.showPreferredPosts(authHandler.getEmail()));
        }
        else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(postRepo.showRecentPosts(0, 100));
        }
    }
    @RequestMapping("/post")
    public Post getPost(@RequestBody String id) {
        int postId = Integer.parseInt(id.split(" ")[0]);
        if(!id.split(" ")[1].equals("null")) {
            int userId = Integer.parseInt(id.split(" ")[1]);
            userRepo.addView(userId,postId);
        }

        postRepo.incrementViews(postId);
        return postRepo.showSpecificPost(postId);
    }

    @RequestMapping("/isfav")
    public String isFav(@RequestBody String id) {
        int postId = Integer.parseInt(id.split(" ")[0]);
        int userId = Integer.parseInt(id.split(" ")[1]);
        return postRepo.checkFavorite(userId,postId);
    }

    @RequestMapping("/user")
    public User getUser(@RequestBody int id) {
           return userRepo.getUser(id);
    }

    @RequestMapping("/addfav")
    public String Add(@RequestBody String id) {
        int postId = Integer.parseInt(id.split(" ")[0]);
        int userId = Integer.parseInt(id.split(" ")[1]);
        if(userRepo.checkExists(userId,postId).length!=0){
            userRepo.deleteSave(userId,postId);
            return "removed";
        }else{
            userRepo.addSave(userId,postId);
            return "added";
        }
    }

    @GetMapping("/topten")
    public List<Post> getTopTenViewedPosts() {
        return postRepo.showTopTenViewedPosts();
    }

}
