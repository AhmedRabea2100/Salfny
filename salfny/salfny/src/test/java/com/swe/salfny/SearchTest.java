package com.swe.salfny;


import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.post.SearchRepository;

import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
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
import static org.springframework.test.util.AssertionErrors.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class SearchTest {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private UserRepository repo;

    int id;


    @BeforeEach
    void clear() {
        User u = new User("Usef Ashraf", null, "zz@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);
        repo.save(u);
        id = Integer.parseInt(repo.findByEmail(u.getEmail()));
        postRepository.deleteAll();
    }
    @Test
    @Order(1)
    public void searchLikeTest(){
        Post post1 =  new Post("BMW", null, 1500000, 0, id, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, id, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> res1 = searchRepository.searchLike("BMW");
        List<Post> res2 = searchRepository.searchLike("Cotton 100%");
        assertEquals("First post",post1,res1.get(0));
        assertEquals("Second post",post2,res2.get(0));



    }

    @Test
    @Order(2)
    public void allCategoryTest(){
        Post post1 =  new Post("BMW", null, 1500000, 0, id, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, id, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> res = searchRepository.allCategory();
        assertTrue("All posts return",res.size()>=2);
    }

    @Test
    @Order(3)
    public void searchByCategoryLikeTest(){
        Post post1 =  new Post("BMW", "null", 1500000, 0, id, LocalDateTime.now());
        Post post2 = new Post("T_shirt", "Cotton 100%", 150, 4, id, LocalDateTime.now());
        Post post3 = new Post("LabTop", "Core i7", 17000, 5, id, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        List<Post> res = searchRepository.searchByCategoryLike("cars","BMW");
        List<Post> res2 = searchRepository.searchByCategoryLike("dresses","Cotton 100%");

        List<Post> res3 = searchRepository.searchByCategoryLike("devices","Core i7");

        List<Post> res4 = searchRepository.searchByCategoryLike("suits","zzzzz");
        assertEquals("A post return",post1,res.get(0));
        assertEquals("A post return",post2,res2.get(0));
        assertEquals("A post return",post3,res3.get(0));
        assertEquals("No posts",0,res4.size());
    }



}
