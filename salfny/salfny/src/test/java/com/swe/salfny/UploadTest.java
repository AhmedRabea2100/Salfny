package com.swe.salfny;

import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class UploadTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository repo;

    //Happy Case :)
    @Test
    public void testUploadPost() {
        //given: Successfully Post Upload
        Post p = new Post("Old Car", "Old Car for rent 3 months.", 20000, 0, 1, LocalDateTime.now());
        //when
        Post savedPost = repo.save(p);
        //then
        Post existPost = entityManager.find(Post.class, savedPost.getId());
        assertThat(savedPost.getTitle()).isEqualTo(existPost.getTitle());

    }


    @Test
    public void testUploading2Posts() {
        //given: Successfully 2 Posts Upload
        Post post1 = new Post("Old Car", "Old Car for rent 3 months.", 20000, 0, 1, LocalDateTime.now());
        Post post2 = new Post("Dell Laptop", "Dell Laptop for rent.", 3000, 5, 1, LocalDateTime.now());
        //when
        Post savedPost1 = repo.save(post1);
        Post savedPost2 = repo.save(post2);
        //then
        Post existPost1 = entityManager.find(Post.class, savedPost1.getId());
        Post existPost2 = entityManager.find(Post.class, savedPost2.getId());
        assertEquals(existPost1.getId() + 1, (existPost2.getId()));
    }
}
