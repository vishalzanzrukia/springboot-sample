package com.vishal.demo.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllRecordsAPI() throws Exception {
        mockMvc.perform(get("/api/product"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRecordsAPI_withSoftDeletedRecordsIncluded() throws Exception {
        mockMvc.perform(get("/api/product?includeDeleted=true"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(5))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteByIdAPI() throws Exception {
        mockMvc.perform(delete("/api/product/{id}", "1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(status().isOk());

        //make sure it's deleted by calling get api
        mockMvc.perform(get("/api/product"))
                .andExpect(jsonPath("$.data.length()").value(2));

    }

    @Test
    void testDeleteByIdAPI_whenEntityWithGivenIdDoesNotExist() throws Exception {
        mockMvc.perform(delete("/api/product/{id}", "100"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("The entity with id 100 not found"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteByIdAPI_whenIdTypeIsWrong() throws Exception {
        mockMvc.perform(delete("/api/product/{id}", "hello"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("id should be of type int, but provided of type java.lang.String"))
                .andExpect(status().isBadRequest());
    }
}
