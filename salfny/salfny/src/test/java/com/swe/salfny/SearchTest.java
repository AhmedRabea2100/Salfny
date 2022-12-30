package com.swe.salfny;


import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.post.SearchRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class SearchTest {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;


    @BeforeEach
    void clear() {
        postRepository.deleteAll();
    }
    @Test
    @Order(1)
    public void searchLikeTest(){
        Post post1 =  new Post("BMW", null, 1500000, 0, 1, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, 1, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> res1 = searchRepository.searchLike("B");
        List<Post> res2 = searchRepository.searchLike("Co");
        List<Post> res3 = searchRepository.searchLike("150");
        List<Post> res4 = searchRepository.searchLike("z");
        assertEquals("First post",post1,res1.get(0));
        assertEquals("Second post",post2,res2.get(0));
        assertEquals("All posts",2,res3.size());
        assertEquals("No posts",0,res4.size());

    }

    @Test
    @Order(2)
    public void allCategoryTest(){
        Post post1 =  new Post("BMW", null, 1500000, 0, 1, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, 1, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> res = searchRepository.allCategory();
        assertEquals("All posts return",2,res.size());
    }

    @Test
    @Order(3)
    public void searchByCategoryLikeTest(){
        Post post1 =  new Post("BMW", null, 1500000, 0, 1, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, 1, LocalDateTime.now());
        Post post3 = new Post("LabTop", "Core i7", 17000, 5, 1, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        List<Post> res = searchRepository.searchByCategoryLike("cars","B");
        List<Post> res1 = searchRepository.searchByCategoryLike("cars","a");
        List<Post> res2 = searchRepository.searchByCategoryLike("dresses","1");
        List<Post> res3 = searchRepository.searchByCategoryLike("dresses","z");
        List<Post> res4 = searchRepository.searchByCategoryLike("devices","Core");
        List<Post> res5 = searchRepository.searchByCategoryLike("dresses","m");
        List<Post> res6 = searchRepository.searchByCategoryLike("suits","a");
        assertEquals("A post return",post1,res.get(0));
        assertEquals("No posts",0,res1.size());
        assertEquals("A post return",post2,res2.get(0));
        assertEquals("No posts",0,res3.size());
        assertEquals("A post return",post3,res4.get(0));
        assertEquals("No posts",0,res5.size());
        assertEquals("No posts",0,res6.size());
    }



}
