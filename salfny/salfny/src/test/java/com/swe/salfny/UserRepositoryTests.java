//package com.swe.salfny;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.swe.salfny.Model.user.User;
//import com.swe.salfny.Model.user.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback()
////@TestPropertySource(locations="classpath:test.properties")
//public class UserRepositoryTests {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository repo;
//
//    // test methods go below
//
//    @Test
//    public void testCreateUser() {
//
//        User user = new User();
//        user.setEmail("ravikumar@gmail.com");
//        user.setPassword("ravi2020");
//        user.setUsername("RaviKumar");
//        user.setPhoneNumber("01012717483");
//        System.out.println("yaraab "+repo);
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//        //System.out.println(+existUser.getEmail());
//       assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
//    @Test
//    public void testCreateUserr() {
//
//        User user = new User();
//        user.setEmail("ravikumarrr@gmail.com");
//        user.setPassword("ravi2020");
//        user.setUsername("RaviKumar");
//        user.setPhoneNumber("01012717483");
//        System.out.println("yaraab "+repo);
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//        //System.out.println(+existUser.getEmail());
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
//    @Test
//    public void testCreateUserrr() {
//
//        User user = new User();
//        user.setEmail("ravikumarrrrrrrr@gmail.com");
//        user.setPassword("ravi2020");
//        user.setUsername("RaviKumar");
//        user.setPhoneNumber("01012717483");
//        System.out.println("yaraab "+repo);
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//        //System.out.println(+existUser.getEmail());
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
//}