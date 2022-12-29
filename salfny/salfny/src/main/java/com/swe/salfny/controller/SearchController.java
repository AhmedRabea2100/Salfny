package com.swe.salfny.controller;


import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.SearchRepository;
import com.swe.salfny.Model.user.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    @RequestMapping("/search")
    public String login(@RequestBody String word) {
        return "Hello World";
    }
}
