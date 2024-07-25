package com.websocketchatbot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WebSocketController.class)
public class WebSocketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWebSocketEndpoint() throws Exception {
        mockMvc.perform(get("/ws"))
               .andExpect(status().isOk())
               .andExpect(content().string("WebSocket endpoint is running. Connect to /chat"));
    }
}
