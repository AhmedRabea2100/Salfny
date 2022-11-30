package com.swe.salfny.controller;

import com.swe.salfny.classes.User;
import com.swe.salfny.classes.UserBuilder;
import com.swe.salfny.classes.UserData;
import com.swe.salfny.classes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class controller {

    private UserBuilder user = new UserBuilder();

    @Autowired
    private UserRepository repo;

    @CrossOrigin
    @RequestMapping("/signUp")
    public boolean createAccount(@RequestParam String fName, @RequestParam String lName, @RequestParam String email, @RequestParam String password, @RequestParam String phoneNumber) {
        UserData userr = repo.findByEmail(email);

        if (userr!=null && userr.getEmail().equals(email))
            return false;
        UserData u = user.build(fName, lName, email, password, phoneNumber);
        UserData savedUser = repo.save(u);
        return true;
    }
}
