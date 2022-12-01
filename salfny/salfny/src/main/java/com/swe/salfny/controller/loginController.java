package com.swe.salfny.controller;

import com.swe.salfny.User.Credential;
import com.swe.salfny.User.UserRepository;
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
        List<Object[]> user = repo.authenticate(email);

        if(user.size() == 0)
            return "Email not found";

        String password = (String)user.get(0)[0];

        if (BCrypt.checkpw(c.getPassword(),password))
            return "Login Successfully";

        return "Incorrect password";
    }

}
