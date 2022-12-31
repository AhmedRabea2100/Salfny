package com.swe.salfny.endpointTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.salfny.Model.post.Post;
import com.swe.salfny.Model.post.PostRepository;

import com.swe.salfny.Model.user.User;
import com.swe.salfny.Model.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class SearchEndpointTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Autowired
    private PostRepository postRepository;
    Post post1;
    Post post2;
    Post post3;
    @Autowired
    UserRepository repo;
    int id;
    User u;
    @BeforeEach
    public void initTest() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        u = new User("Usef Ashraf", null, "zz@gmail.com", null, LocalDateTime.now(), "123456789", "01150161459", 3, 3);
        repo.save(u);
        id = Integer.parseInt(repo.findByEmail(u.getEmail()));
        post1 =  new Post("BMW", "null", 1500000, 0, id, LocalDateTime.now());
        postRepository.save(post1);
        post2 =  new Post("T_shirt", "Cotton 100%", 150, 4, id, LocalDateTime.now());
        postRepository.save(post2);
        post3 =  new Post("LabTopp", "Core i7", 17000, 5, id, LocalDateTime.now());
        postRepository.save(post3);
    }
    @AfterEach
    public void endTest(){
        postRepository.delete(post1);
        postRepository.delete(post2);
        postRepository.delete(post3);
        repo.delete(u);
    }

    @Test
    @Order(1)
    public void searchLike_foundPosts() throws Exception{
                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content("BMW@All").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isArray())
                .andExpect((jsonPath("$..title", Matchers.containsInAnyOrder("BMW"))));

    }
    @Test
    @Order(2)
    public void searchLike_notFoundPosts() throws Exception{

        mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content("ZXzzzzzzzzzzzzzzz@All").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isEmpty());
    }

    @Test
    @Order(3)
    public void allCategory() throws Exception{
                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content(" @All").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isArray());
    }


    @Test
    @Order(4)
    public void searchByCategoryLike_foundPosts() throws Exception{
                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content("LabTopp@devices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isArray());

                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content(" @dresses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isArray());
    }

    @Test
    @Order(5)
    public void searchByCategoryLike_notFoundPosts() throws Exception{
                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content("ZXxx@devices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isEmpty());

                mvc.perform(MockMvcRequestBuilders
                        .post("/search")
                        .content("zzxx s@others").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title").isEmpty());
    }









}
