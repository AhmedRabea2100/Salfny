package com.swe.salfny.controller;

import com.swe.salfny.Model.preferences.Preferences;
import com.swe.salfny.Model.preferences.PreferencesRepository;
import com.swe.salfny.Model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferencesController {
    @Autowired
    private PreferencesRepository repo;

    @Autowired
    private UserRepository user_repo;

    @RequestMapping("/preferences")
    public String createAccount(@RequestBody String p) {

        p=p.replaceAll("}]","");
        p=p.replaceAll("\\[\\{","");
        String[] parse1 = p.split("},\\{");
        String user_email=parse1[parse1.length-1].replaceAll("\"user_email\":\"","");
        user_email=user_email.substring(0,user_email.length()-1);
        int id = Integer.parseInt(user_repo.findByEmail(user_email));

        for (int i=0;i< parse1.length-1;i++){
            parse1[i]=parse1[i].replaceAll("\"id\":","");
            int ind=parse1[i].indexOf(",");
            repo.insert(id,Integer.parseInt(parse1[i].substring(0,ind))-1);

        }
        return "Registration Succeeded";
    }
}
