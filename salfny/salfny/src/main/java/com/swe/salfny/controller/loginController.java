package com.swe.salfny.controller;

import com.swe.salfny.user.Credential;
import com.swe.salfny.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class loginController {

    @Autowired
    private UserRepository repo;

    @RequestMapping("/login")
    public String login(@RequestBody Credential c){
        String email = c.getEmail();
        String password = repo.authenticate(email);

        if(password == null)
            return "Email not found";


        if (BCrypt.checkpw(c.getPassword(),password))
            return "Login Successfully";

        return "Incorrect password";
    }

}
