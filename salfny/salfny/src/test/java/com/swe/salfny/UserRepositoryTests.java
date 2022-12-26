package com.swe.salfny;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

import com.swe.salfny.Model.user.Credential;
import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback()
//@TestPropertySource(locations="classpath:test.properties")
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    

    @Autowired
    private UserRepository repo;

    User u;

    // test methods go below
    @BeforeEach
    public void createUser() {
        User u = new User("Usef Ashraf", null, "usef@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);
        repo.save(u);
    }

    @Test
    @Order(1)
    public void createAccountTest() {

        User  user = new User("Usef Ashraf", null, "usef1@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);

        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertEquals("Registration Succeeded",user.getEmail(),existUser.getEmail());

    }
    @Test
    @Order(2)
    public void AccountIsFoundTest() {

        User user = new User("Usef Ashraf", null, "usef@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);

        String id = repo.findByEmail(user.getEmail());

        assertNotEquals("This Email is already used",id,null);
    }
    @Test
    @Order(3)
    public void getProfileTest() {
        Credential c = new Credential();
        c.setEmail("usef@gmail.com");
        c.setPassword("123456789");

        User user = repo.getProfile(c.getEmail());
        assertNotEquals("ok",user,null);


    }

    @Test
    @Order(4)
    public void profileNotFoundTest() {
        Credential c = new Credential();
        c.setEmail("u@gmail.com");
        c.setPassword("123456789");
        User user = repo.getProfile(c.getEmail());
        assertEquals("ok",user,null);


    }
}