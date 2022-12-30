package com.swe.salfny;

import com.swe.salfny.Model.photo.Photo;
import com.swe.salfny.Model.photo.PhotoRepository;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class UploadPostTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    PhotoRepository repoPhoto;

    @Autowired
    private UserRepository repo;

    int id;

    @BeforeEach
    void initTest() {


        User u = new User("Usef Ashraf", null, "zzzz@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);
        repo.save(u);
        id = Integer.parseInt(repo.findByEmail(u.getEmail()));

    }

    //Happy Case :)
    @Test
    public void testUploadPost() {
        //given: Successfully Post Upload
        Post p = new Post("Old Car", "Old Car for rent 3 months.", 20000, 0, id, LocalDateTime.now());
        //when
        Post savedPost = postRepository.save(p);
        Photo photo1 = new Photo(postRepository.findMaxId(), "2.png");
        Photo savedPhoto = repoPhoto.save(photo1);
        //then
        Post existPost = entityManager.find(Post.class, savedPost.getId());
        Photo existPhoto1 = entityManager.find(Photo.class, photo1.getId());
        assertThat(savedPost.getTitle()).isEqualTo(existPost.getTitle());
        assertThat(savedPhoto.getId()).isEqualTo(existPhoto1.getId());

    }


    @Test
    public void testUploading2Posts() {
        //given: Successfully 2 Posts Upload
        Post post1 = new Post("Old Car", "Old Car for rent 3 months.", 20000, 0, id, LocalDateTime.now());
        Post savedPost1 = postRepository.save(post1);
        Photo photo1 = new Photo(postRepository.findMaxId(), "2.png");
        Photo savedPhoto1 = repoPhoto.save(photo1);
        Post post2 = new Post("Dell Laptop", "Dell Laptop for rent.", 3000, 5, id, LocalDateTime.now());
        //when
        Post savedPost2 = postRepository.save(post2);
        Photo photo2 = new Photo(postRepository.findMaxId(), "3.png");
        Photo savedPhoto2 = repoPhoto.save(photo2);
        //then
        Post existPost1 = entityManager.find(Post.class, savedPost1.getId());
        Photo existPhoto1 = entityManager.find(Photo.class, savedPhoto1.getId());
        Post existPost2 = entityManager.find(Post.class, savedPost2.getId());
        Photo existPhoto2 = entityManager.find(Photo.class, savedPhoto2.getId());
        assertEquals("Two posts have id incremental", existPost1.getId() + 1,existPost2.getId());

        assertEquals("Two posts have id incremental",existPhoto1.getId() + 1, (existPhoto2.getId()));
    }
    @Test
    public void testUploadingPosts() {

        Post p = new Post("Old Car", "Old Car for rent 3 months.", 20000, 0, id, LocalDateTime.now());
        Post savedPost1 = postRepository.save(p);
        Photo photo1 = new Photo(postRepository.findMaxId(), "2.png");
        Photo savedPhoto1 = repoPhoto.save(photo1);
        Post post2 = new Post("Dell Laptop", "Dell Laptop for rent.", 3000, 5, id, LocalDateTime.now());
        Post savedPost2 = postRepository.save(post2);
        Photo photo2 = new Photo(postRepository.findMaxId(), "3.png");
        Photo savedPhoto2 = repoPhoto.save(photo2);


        Post existPost2 = entityManager.find(Post.class, savedPost2.getId());
        Photo existPhoto2 = entityManager.find(Photo.class, savedPhoto2.getId());

        assertNotEquals(savedPost1.getTitle(), existPost2.getTitle());
        assertNotEquals((savedPhoto1.getId()), existPhoto2.getId());

    }
}
