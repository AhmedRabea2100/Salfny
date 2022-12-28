
package com.swe.salfny;

import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback()
public class HomePageTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository repo;

    @BeforeEach
    void clear() {
        repo.deleteAll();
    }

    @Test
    @Order(1)
    public void zeroPosts() {
        List<Post> result = repo.showRecentPosts(0, 3);
        assertEquals("Size = 0", 0, result.size());
    }

    @Test
    @Order(2)
    public void onePost() {
        Post post = new Post("title", "null", 15, 1, 1, LocalDateTime.now());
        repo.save(post);
        List<Post> result = repo.showRecentPosts(0, 3);
        assertEquals("Size = 1", 1, result.size());
        assertTrue("Same post", samePost(post, result.get(0)));
    }

    @Test
    @Order(3)
    public void threePostsSameOrder() {
        Post post1 = new Post("title1", "null", 15, 1, 1, LocalDateTime.now());
        Post post2 = new Post("title2", "null", 20, 1, 1, LocalDateTime.now());
        Post post3 = new Post("title3", "null", 25, 1, 1, LocalDateTime.now());
        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        List<Post> result = repo.showRecentPosts(0, 3);
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post1, result.get(0)));
        assertTrue("2nd", samePost(post2, result.get(1)));
        assertTrue("3rd", samePost(post3, result.get(2)));
    }

    @Test
    @Order(4)
    public void threePostsDifferentOrder() {
        Post post1 = new Post("title1", null, 15, 0, 1, LocalDateTime.of(LocalDate.parse("2003-01-01"), LocalTime.now()));
        Post post2 = new Post("title2", "null", 20, 1, 1, LocalDateTime.of(LocalDate.parse("2007-01-01"), LocalTime.now()));
        Post post3 = new Post("title3", "f", 25, 2, 1, LocalDateTime.of(LocalDate.parse("2005-02-01"), LocalTime.now()));

        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        List<Post> result = repo.showRecentPosts(0, 3);
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post2, result.get(0)));
        assertTrue("2nd", samePost(post3, result.get(1)));
        assertTrue("3rd", samePost(post1, result.get(2)));
    }

    @Test
    @Order(5)
    public void fourPostsDifferentOrderPageOne() {
        Post post1 = new Post("title1", null, 15, 0, 1, LocalDateTime.of(LocalDate.parse("2003-01-01"), LocalTime.now()));
        Post post2 = new Post("title2", "null", 20, 1, 1, LocalDateTime.of(LocalDate.parse("2007-01-01"), LocalTime.now()));
        Post post3 = new Post("title3", "f", 25, 2, 1, LocalDateTime.of(LocalDate.parse("2005-02-01"), LocalTime.now()));
        Post post4 = new Post("title4", "", 30, 4, 1, LocalDateTime.of(LocalDate.parse("2010-02-01"), LocalTime.now()));
        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        repo.save(post4);
        List<Post> result = repo.showRecentPosts(0, 3);
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post4, result.get(0)));
        assertTrue("2nd", samePost(post2, result.get(1)));
        assertTrue("3rd", samePost(post3, result.get(2)));
    }

    @Test
    @Order(6)
    public void showTopTenTest() {
        for (int i = 1; i <= 12; i++) {
            Post post = new Post("title" + i, null, 5 * i, null, i, LocalDateTime.now(), 1, 1);
            repo.save(post);
        }
        List<Post> result = repo.showTopTenViewedPosts();
        assertEquals("Size = 10", 10, result.size());
        assertEquals("top View", 12, result.get(0).getViews());
    }


    private boolean samePost(Post post1, Post post2) {
        return post1.getTitle().equals(post2.getTitle()) &&
                ((post1.getDescription() == null && post2.getDescription() == null) || post1.getDescription().equals(post2.getDescription())) &&
                post1.getPrice() == post2.getPrice() &&
                ((post1.getPayment_option() == null && post2.getPayment_option() == null) || post1.getPayment_option().equals(post2.getPayment_option())) &&
                post1.getViews() == post2.getViews() &&
                post1.getDate().equals(post2.getDate()) &&
                post1.getCategory_id() == post2.getCategory_id() &&
                post1.getUser_id() == post2.getUser_id();
    }
}
