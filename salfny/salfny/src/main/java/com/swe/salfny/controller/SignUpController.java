package com.swe.salfny.controller;

import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

@RestController
public class SignUpController {

    @Autowired
    private UserRepository repo;
    private final int LOG_ROUNDS = 12;

    //@CrossOrigin
    @RequestMapping("/signup")
    public String createAccount(@RequestBody User u) {

        String id = repo.findByEmail(u.getEmail());

        // check email is not duplicated
        if (id != null)
            return "This Email is already used";

        u.setMemberSince(LocalDateTime.now());
        u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(LOG_ROUNDS)));

        // try to store to database
        try {
            repo.save(u);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Registration Succeeded";
    }
}
