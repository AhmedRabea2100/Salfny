package com.swe.salfny.controller;

import com.swe.salfny.User.Credential;
import com.swe.salfny.User.UserData;
import com.swe.salfny.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class controller {

    @Autowired
    private UserRepository repo;

    //@CrossOrigin
    @RequestMapping("/signUp")
    public boolean createAccount(@RequestBody UserData u) {
        UserData userr = repo.findByEmail(u.getEmail());
        // check email is not duplicated
        if (userr!=null && userr.getEmail().equals(u.getEmail()))
            return false;
        // store to database
        UserData savedUser = repo.save(u);
        return true;
    }

    //@CrossOrigin
    @RequestMapping("/login")
    public boolean login(@RequestBody Credential c){
        String email = c.getEmail();
        String password  = c.getPassword();
        UserData user = repo.authenticate(email, password);
        if(user == null)
            return false;
        return true;
    }
}
