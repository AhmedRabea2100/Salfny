package com.swe.salfny;


import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.post.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class SearchTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;


    @BeforeEach
    void clear() {
        postRepository.deleteAll();
    }
    @Test
    public void searchTest(){
//        for(int i = 1;i<=12;i++)
//        {
//            Post post =  new Post("m" + i, "mo" + i, 5*i,null, i, LocalDateTime.now(),i%3,1);
//            postRepository.save(post);
//        }
        Post post1 =  new Post("a", "b", 15, 0, 1, LocalDateTime.now());
        Post post2 = new Post("b", "a", 20, 1, 1, LocalDateTime.now());
        postRepository.save(post1);
        postRepository.save(post2);
        List<Post> res = searchRepository.searchByCategoryLike("cars");
        assertEquals("true",3,res.size());
    }
}
