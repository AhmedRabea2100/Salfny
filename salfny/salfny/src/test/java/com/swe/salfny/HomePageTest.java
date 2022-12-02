package com.swe.salfny;

import com.swe.salfny.post.Post;
import com.swe.salfny.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

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

    @Test
    public void zeroPosts() {
        List<Post> result = repo.showRecentPosts();
        assertEquals("Size = 0", 0, result.size());
    }

    @Test
    public void onePost() {
        Post post = new Post(1, "title", null, 15, null, 3, "2000-01-01", 1, 1);
        repo.save(post);
        List<Post> result = repo.showRecentPosts();
        assertEquals("Size = 1", 1, result.size());
        assertTrue("Same post", samePost(post, result.get(0)));
    }

    @Test
    public void threePostsSameOrder() {
        Post post1 = new Post(1, "title1", null, 15, null, 3, "2000-01-01", 1, 1);
        Post post2 = new Post(2, "title2", "null", 16, null, 4, "2003-01-01", 1, 1);
        Post post3 = new Post(3, "title3", "f", 17, null, 1, "2005-02-01", 1, 1);
        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        List<Post> result = repo.showRecentPosts();
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post1, result.get(0)));
        assertTrue("2nd", samePost(post2, result.get(1)));
        assertTrue("3rd", samePost(post3, result.get(2)));
    }

    @Test
    public void threePostsDifferentOrder() {
        Post post1 = new Post(1, "title1", null, 15, null, 3, "2007-01-01", 1, 1);
        Post post2 = new Post(2, "title2", "null", 16, null, 4, "2003-01-01", 1, 1);
        Post post3 = new Post(3, "title3", "f", 17, null, 1, "2005-02-01", 1, 1);
        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        List<Post> result = repo.showRecentPosts();
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post2, result.get(0)));
        assertTrue("2nd", samePost(post3, result.get(1)));
        assertTrue("3rd", samePost(post1, result.get(2)));
    }

    @Test
    public void fourPostsDifferentOrder() {
        Post post1 = new Post(1, "title1", null, 15, null, 3, "2007-01-01", 1, 1);
        Post post2 = new Post(2, "title2", "null", 16, null, 4, "2003-01-01", 1, 1);
        Post post3 = new Post(3, "title3", "f", 17, null, 1, "2005-02-01", 1, 1);
        Post post4 = new Post(4, "title4", "", 1, 2, 1, "2000-02-01", 1, 1);
        repo.save(post1);
        repo.save(post2);
        repo.save(post3);
        repo.save(post4);
        List<Post> result = repo.showRecentPosts();
        assertEquals("Size = 3", 3, result.size());
        assertTrue("1st", samePost(post4, result.get(0)));
        assertTrue("2nd", samePost(post2, result.get(1)));
        assertTrue("3rd", samePost(post3, result.get(2)));
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
