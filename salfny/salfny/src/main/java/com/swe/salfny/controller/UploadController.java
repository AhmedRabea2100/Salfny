package com.swe.salfny.controller;

import com.swe.salfny.Model.Uplo;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

@RestController
public class UploadController {


    //@CrossOrigin
    @RequestMapping("/upload")
    public String upload(@RequestBody String u,@RequestBody Uplo p ) {
        System.out.println(p.getTitle()+" "+ p.getDescription()+" "+p.getCategory());
        return "back  " ;

    }
}
