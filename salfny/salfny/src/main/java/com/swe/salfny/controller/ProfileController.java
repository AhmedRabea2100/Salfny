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

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private AuthHandler authHandler;

    @RequestMapping("/userPosts")
    public ResponseEntity<List<Post>> userPosts(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null && !token.equals("null") && authHandler.validateToken(token)) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(postRepo.getUserPosts(Integer.valueOf(repo.findByEmail(authHandler.getEmail()))));
        } else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(null);
        }
    }

    @RequestMapping("/userFavPosts")
    public ResponseEntity<List<Post>> userFavPosts(@RequestHeader(name = "Authorization", required = false) String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null && !token.equals("null") && authHandler.validateToken(token)) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(postRepo.getUserFavPosts(Integer.valueOf(repo.findByEmail(authHandler.getEmail()))));
        } else {
            return ResponseEntity.status(401)
                    .headers(headers)
                    .body(null);
        }
    }

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
    public String updateUser(@RequestBody User u) {
        User user = repo.getProfile(u.getEmail());

        // update profile picture
        if (u.getProfilePic() != null && u.getProfilePic().length() > 50) {
            String path = StorePhotoInPath(u.getProfilePic(), u.getEmail());
            user.setProfilePic(path);
        }
        user.update(u);

        // try to store to database
        try {
            repo.save(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Saved";
    }


    public String StorePhotoInPath(String photo, String e) {
        String base64String = photo;
        String[] strings = base64String.split(",");
        String extension = switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64" -> "jpeg";
            case "data:image/png;base64" -> "png";
            default ->//should write cases for more images types
                    "jpg";
        };
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String[] s1 = System.getProperty("user.dir").split("salfny");
        String out;
        if (s1[0].contains("/")) {
            out = s1[0] + "Frontend/src/assets/db/" + e + "." + extension;//for Linux Users
        } else {
            out = s1[0] + "Frontend\\src\\assets\\db\\" + e + "." + extension;//for Windows Users
        }
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out))) {
            outputStream.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return e + "." + extension;
    }
    @RequestMapping("/remove")
    public String remove(@RequestHeader(name = "Authorization", required = true) String token,@RequestBody String id) {
        if (token != null && !token.equals("null") && authHandler.validateToken(token)) {
            if(repo.findByEmail(authHandler.getEmail()).equals(postRepo.getOwner(id))) {//check that the person who tries to remove the post is the owner
                postRepo.remove(id);
                return "true";
            }
        }
        return "false";
    }
}
