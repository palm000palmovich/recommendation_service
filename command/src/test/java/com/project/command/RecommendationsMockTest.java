package com.project.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.command.controllers.RecommendationsController;
import com.project.command.model.Products;
import com.project.command.repository.UsersDataRepository;
import com.project.command.services.RecommendationsService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RecommendationsMockTest.class)
public class RecommendationsMockTest {

    private MockMvc mockMvc;

    @Mock
    private RecommendationsService recommendationsService;

    @InjectMocks
    private RecommendationsController recommendationsController;

    @Autowired
    private ObjectMapper objectMapper;

    private String userId;
    private Products product = new Products();



    @BeforeEach
    public void setUp(){
         this.product.setName("Invest500");
         this.product.setDescription("Text1");

         this.userId = "cd515076-5d8a-44be-930e-8d4fcb79f42d";

        mockMvc = MockMvcBuilders.standaloneSetup(recommendationsController).build();
    }



    @Test
    public void testGetUsersRecInfo() throws Exception{
        List<Products> prodList = List.of(product);

        when(recommendationsService.getRecForUser(anyString())).thenReturn(prodList);

        mockMvc.perform(get("/recommendations/{user_id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Invest500"))
                .andExpect(jsonPath("$[0].description").value("Text1"));
    }

    @Test
    public void TestGetGetUsersRecInfo_NotFound() throws Exception{
        when(recommendationsService.getRecForUser(anyString())).thenReturn(null);

        mockMvc.perform(get("/recommendations/{user_id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



}
