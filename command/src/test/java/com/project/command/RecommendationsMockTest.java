package com.project.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RecommendationsMockTest.class)
public class RecommendationsMockTest {

    private MockMvc mockMvc;

    /*@Mock
    private RecommendationsService recommendationsService;

    @InjectMocks
    private RecommendationsController recommendationsController;

    @Autowired
    private ObjectMapper objectMapper;

    private String userId;
    //private RecommendationsDTO recommendationsDTO;



    @BeforeEach
    public void setUp(){
        //this.recommendationsDTO = new RecommendationsDTO("Invest500", "Text1");

        this.userId = "cd515076-5d8a-44be-930e-8d4fcb79f42d";

        mockMvc = MockMvcBuilders.standaloneSetup(recommendationsController).build();
    }



    @Test
    public void testGetUsersRecInfo() throws Exception{
        //List<RecommendationsDTO> recList = List.of(recommendationsDTO);

        //when(recommendationsService.getRecsById(anyString())).thenReturn(recList);

        mockMvc.perform(get("/recommendation/{useId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productName").value("Invest500"))
                .andExpect(jsonPath("$[0].productText").value("Text1"));
    }

    @Test
    public void TestGetGetUsersRecInfo_NotFound() throws Exception{
        //when(recommendationsService.getRecsById(anyString())).thenReturn(null);

        mockMvc.perform(get("/recommendation/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }*/



}
