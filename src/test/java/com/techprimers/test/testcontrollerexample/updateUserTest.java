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
public class updateUserTest {

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
                "  \"userId\": \"26\",\n" +
                "  \"userDateOfBirth\": \"1997/01/30\",\n" +
                "  \"userJob\": \"Worker\",\n" +
                "  \"userAddress\": \"25 King St W,Toronto,ON\",\n" +
                "  \"promotionalMessage\": \"null\"\n" +
                "}";

        mockMvc.perform(post("/hello/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Ali")))
                .andExpect(jsonPath("$.userId", Matchers.is("26")))
                .andExpect(jsonPath("$.userDateOfBirth", Matchers.is("1997/01/30")))
                .andExpect(jsonPath("$.userJob", Matchers.is("Worker")))
                .andExpect(jsonPath("$.userAddress", Matchers.is("25 King St W,Toronto,ON")))
                .andExpect(jsonPath("$.promotionalMessage", Matchers.is("null")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(6)));
    }



}
