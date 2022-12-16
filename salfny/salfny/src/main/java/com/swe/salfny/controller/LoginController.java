package com.swe.salfny.controller;

import com.swe.salfny.AuthHandler;
import com.swe.salfny.user.Credential;
import com.swe.salfny.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthHandler authHandler;

    @RequestMapping("/login")
    public String login(@RequestBody Credential c) {
        String email = c.getEmail();
        String password = repo.authenticate(email);

        if (password == null)
            return "Email not found";

        if (BCrypt.checkpw(c.getPassword(), password)) {
            return authHandler.generateToken(c);
        }
        return "Incorrect password";
    }
}
