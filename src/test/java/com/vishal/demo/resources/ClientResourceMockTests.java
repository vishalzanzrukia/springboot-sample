package com.vishal.demo.resources;

import com.vishal.demo.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {ClientResourceMockTests.TestConfig.class})
@AutoConfigureMockMvc
public class ClientResourceMockTests {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    public static class TestConfig {
        @Bean
        @Primary
        public ClientService clientService2() {
            ClientService service = Mockito.spy(ClientService.class);
            Mockito.doThrow(IllegalStateException.class).when(service).findAll();
            return service;
        }
    }

    @Test
    void testGetAllRecordsAPI() throws Exception {
        mockMvc.perform(get("/api/client"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("There is technical issue with the api, please contact admin"))
                .andExpect(status().isInternalServerError());
    }

}
