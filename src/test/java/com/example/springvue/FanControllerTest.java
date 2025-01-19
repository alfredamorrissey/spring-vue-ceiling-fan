package com.example.springvue;

import com.example.springvue.model.Fan;
import com.example.springvue.service.FanService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FanService fanService;

    @BeforeEach
    public void setUp() throws Exception {
        fanService.deleteAllFans();
    }

    @Test
    public void testCreateFan() throws Exception {
        String fanJson = """
                {
                    "speed": 2,
                    "direction": "clockwise"
                }
                """;

        String jsonResponse = mockMvc.perform(post("/api/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fanJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.speed").value(2))
                .andExpect(jsonPath("$.direction").value("clockwise"))
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void testGetFanById() throws Exception {
        String createFanJson = """
                {
                    "speed": 3,
                    "direction": "clockwise"
                }
                """;

        String jsonResponse = mockMvc.perform(post("/api/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createFanJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.speed").value(3))
                .andExpect(jsonPath("$.direction").value("clockwise"))
                .andReturn().getResponse().getContentAsString();

        // Extract the ID from the response
        int fanId = JsonPath.read(jsonResponse, "$.id");

        mockMvc.perform(get("/api/fans/{id}", fanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.speed").value(3))
                .andExpect(jsonPath("$.direction").value("clockwise"));
    }

    @Test
    public void testUpdateFan() throws Exception {
        String createFanJson = """
                {
                    "speed": 1,
                    "direction": "clockwise"
                }
                """;

        String jsonResponse = mockMvc.perform(post("/api/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createFanJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.speed").value(1))
                .andExpect(jsonPath("$.direction").value("clockwise"))
                .andReturn().getResponse().getContentAsString();

        // Extract the ID from the response
        int fanId = JsonPath.read(jsonResponse, "$.id");

        String updateFanJson = """
                {
                    "speed": 3,
                    "direction": "counter-clockwise"
                }
                """;

        mockMvc.perform(put("/api/fans/{id}", fanId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFanJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.speed").value(3))
                .andExpect(jsonPath("$.direction").value("counter-clockwise"));
    }

    @Test
    public void testDeleteFan() throws Exception {
        String createFanJson = """
                {
                    "speed": 1,
                    "direction": "clockwise"
                }
                """;

        String jsonResponse = mockMvc.perform(post("/api/fans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createFanJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.speed").value(1))
                .andExpect(jsonPath("$.direction").value("clockwise"))
                .andReturn().getResponse().getContentAsString();

        // Extract the ID from the response
        int fanId = JsonPath.read(jsonResponse, "$.id");

        mockMvc.perform(delete("/api/fans/{id}", fanId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/fans/{id}", fanId))
                .andExpect(status().isNotFound());
    }
}
