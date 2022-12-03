package com.swe.salfny;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

import com.swe.salfny.user.Credential;
import com.swe.salfny.user.UserData;
import com.swe.salfny.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.annotation.Rollback;




@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback()
public class loginTest {

    @Autowired
    private UserRepository repo;

    UserData user;

    @BeforeEach
    public void createUser(){
        user = new UserData();
        user.setEmail("usef@gmail.com");
        user.setPassword("123456");
        user.setPhoneNumber("01150161459");
        user.setUsername("Usef Ashraf");
        repo.save(user);
    }

    @AfterEach
    public void deleteUser(){
        user = null;
    }


    @Test
    @Order(1)
    public void correctLoginTest(){
        Credential c = new Credential();
        c.setEmail("usef@gmail.com");
        c.setPassword("123456");
        String password = repo.authenticate(c.getEmail());

        assertEquals("Pass is correct",c.getPassword(),password);
    }

    @Test
    @Order(2)
    public void wrongPassLoginTest(){
        Credential c = new Credential();
        c.setEmail("usef@gmail.com");
        c.setPassword("1234569");
        String password = repo.authenticate(c.getEmail());
        assertNotEquals("Pass is not correct",c.getPassword(),password);
    }

    @Test
    @Order(3)
    public void wrongUserNameLoginTest(){
        Credential c = new Credential();
        c.setEmail("j@gmail.com");
        c.setPassword("1234569");
        String password = repo.authenticate(c.getEmail());

        assertEquals("Email not found",password,null);


    }


}
