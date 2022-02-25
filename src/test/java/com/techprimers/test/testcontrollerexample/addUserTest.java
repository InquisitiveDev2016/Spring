package com.techprimers.test.testcontrollerexample;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class addUserTest {


    private MockMvc mockMvc;

    @Mock
    private HelloService helloService;

    @InjectMocks
    private HelloResource helloResource;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloResource)
                .build();
    }


    @Test
    public void testPost() throws Exception {

        String json = "{\n" +
                "  \"title\": \"Ali\",\n" +
                "  \"userId\": \"5\",\n" +
                "  \"userDateOfBirth\": \"1997/01/30\",\n" +
                "  \"userJob\": \"Worker\",\n" +
                "  \"userAddress\": \"25 King St W,Toronto,ON\",\n" +
                "  \"promotionalMessage\": \"none\"\n" +
                "}";

        mockMvc.perform(post("/hello/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Ali")))
                .andExpect(jsonPath("$.userId", Matchers.is("5")))
                .andExpect(jsonPath("$.userDateOfBirth", Matchers.is("1997/01/30")))
                .andExpect(jsonPath("$.userJob", Matchers.is("Worker")))
                .andExpect(jsonPath("$.userAddress", Matchers.is("25 King St W,Toronto,ON")))
                .andExpect(jsonPath("$.promotionalMessage", Matchers.is("none")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(6)));
    }

    @Test
    public void testPostStatusCodeOk() throws Exception {

        String json = "{\n" +
                "  \"title\": \"Bob\",\n" +
                "  \"userId\": \"55\",\n" +
                "  \"userDateOfBirth\": \"2017/01/30\",\n" +
                "  \"userJob\": \"Sweeper\",\n" +
                "  \"userAddress\": \"96 Oakley Dr,Toronto,ON\",\n" +
                "  \"promotionalMessage\": \"none\"\n" +
                "}";

        mockMvc.perform(post("/hello/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostStatusCodeNotFound() throws Exception {

        String json = "{\n" +
                "  \"title\": \"Bob\",\n" +
                "  \"userId\": \"55\",\n" +
                "  \"userDateOfBirth\": \"2017/01/30\",\n" +
                "  \"userJob\": \"Worker\",\n" +
                "  \"userAddress\": \"96 Oakley Dr,Toronto,ON\",\n" +
                "  \"promotionalMessage\": \"null\"\n" +
                "}";

        mockMvc.perform(post("/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }


    @Test
    public void checkPromoMsgAppearsForAddress() throws Exception {

        String json = "{\n" +
                "  \"title\": \"Coby\",\n" +
                "  \"userId\": \"28\",\n" +
                "  \"userDateOfBirth\": \"2000/05/30\",\n" +
                "  \"userJob\": \"Cleaner\",\n" +
                "  \"userAddress\": \"15 Queen St E,Brampton,ON\",\n" +
                "  \"promotionalMessage\": \"none\"\n" +
                "}";

        mockMvc.perform(post("/hello/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }




}