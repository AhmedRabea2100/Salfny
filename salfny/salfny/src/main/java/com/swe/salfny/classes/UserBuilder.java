package com.swe.salfny.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class UserBuilder {
    private UserData user;

    @Autowired
    private UserRepository repo ;

    public UserData build(String fName, String lName, String email, String password, String phoneNumber) {
        UserData user = new UserData();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setPhoneNumber(phoneNumber);
       /* System.out.println("hh "+repo);
        UserData savedUser=repo.save(user);
        UserData userr = repo.findByEmail(email);
        if (userr.getId()==savedUser.getId())
            return false;
        return true;*/
        return user;
    }

}
