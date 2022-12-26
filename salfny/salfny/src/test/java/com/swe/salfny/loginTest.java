package com.swe.salfny;

import com.swe.salfny.user.Credential;
import com.swe.salfny.user.User;
import com.swe.salfny.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback()
public class LoginTest {

    @Autowired
    private UserRepository repo;

    @BeforeEach
    public void createUser() {
        User user = new User("Usef Ashraf", null, "usef@gmail.com", null, LocalDateTime.now(), "123456", "01150161459", 3, 3);
        repo.save(user);
    }

    @Test
    @Order(1)
    public void correctLoginTest() {
        Credential c = new Credential();
        c.setEmail("usef@gmail.com");
        c.setPassword("123456");
        String password = repo.authenticate(c.getEmail());
        assertEquals("Pass is correct", c.getPassword(), password);
    }

    @Test
    @Order(2)
    public void wrongPassLoginTest() {
        Credential c = new Credential();
        c.setEmail("usef@gmail.com");
        c.setPassword("1234569");
        String password = repo.authenticate(c.getEmail());
        assertNotEquals("Pass is not correct", c.getPassword(), password);
    }

    @Test
    @Order(3)
    public void wrongUserNameLoginTest() {
        Credential c = new Credential();
        c.setEmail("j@gmail.com");
        c.setPassword("1234569");
        String password = repo.authenticate(c.getEmail());
        assertEquals("Email not found", password, null);
    }
}