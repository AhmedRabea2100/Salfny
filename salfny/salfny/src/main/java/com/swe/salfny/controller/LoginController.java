package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.Model.user.Credential;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LoginController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @RequestMapping("/login")
    public String login(@RequestBody Credential c) {
        String email = c.getEmail();
        String c2 = repo.authenticate(email);

        if (c2 == null)
            return "Email not found";

        if (BCrypt.checkpw(c.getPassword(), c2.substring(c2.indexOf(',')+1))) {
            return authHandler.generateToken(c)+" "+c2.substring(0,c2.indexOf(','));
        }
        return "Incorrect password";
    }
}
