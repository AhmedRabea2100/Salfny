package com.swe.salfny.controller;


import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.SearchRepository;
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
    public List <Post> search(@RequestBody String word) {
        int index = word.lastIndexOf("@");
        String category = word.substring(index+1);
        word = word.substring(0,index);
        if(word.equals(" ")) word = "";
        if(category.equals("All")){
            if(word.equals("")) return searchRepository.allCategory();
            else return searchRepository.searchLike(word);
        }
        return searchRepository.searchByCategoryLike(category,word);
        
    }

//    @RequestMapping("/category")
//    public List <Post> category(@RequestBody String word) {
//        if(word.equals("All"))
//            return searchRepository.allCategory();
//        return searchRepository.searchByCategoryLike(word,"");
//    }


}



