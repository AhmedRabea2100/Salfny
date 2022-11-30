package com.swe.salfny.controller;

import com.swe.salfny.User.UserData;
import com.swe.salfny.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class controller {

    @Autowired
    private UserRepository repo;

    @CrossOrigin
    @RequestMapping("/signUp")
    public boolean createAccount(@RequestBody UserData u) {
        UserData userr = repo.findByEmail(u.getEmail());

        if (userr!=null && userr.getEmail().equals(u.getEmail()))
            return false;
//        UserData u = user.build(fName, lName, email, password, phoneNumber);
        UserData savedUser = repo.save(u);
        return true;
    }
}
