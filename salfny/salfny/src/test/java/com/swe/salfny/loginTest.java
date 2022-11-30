package com.swe.salfny;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

import com.swe.salfny.User.Credential;
import com.swe.salfny.User.UserData;
import com.swe.salfny.User.UserRepository;
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
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;


    @Test
    @Order(1)
    public void loginTest1(){
        Credential c = new Credential();
        c.setEmail("jo@gmail.com");
        c.setPassword("123456789");
        String pass = repo.findByEmail(c.getEmail()).getPassword();
        assertEquals("Pass is correct",c.getPassword(),pass);
    }

    @Test
    @Order(2)
    public void loginTest2(){
        Credential c = new Credential();
        c.setEmail("jo@gmail.com");
        c.setPassword("1234569");
        String pass = repo.findByEmail(c.getEmail()).getPassword();
        assertNotEquals("Pass is not correct",pass,c.getPassword());
    }

    @Test
    @Order(3)
    public void loginTest3(){
        Credential c = new Credential();
        c.setEmail("j@gmail.com");
        c.setPassword("1234569");
        assertEquals("Email not found",repo.authenticate(c.getEmail(),c.getPassword()),null);


    }


}
