package com.websocketchatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @GetMapping("/ws")
    public String getWebSocketEndpoint() {
        return "WebSocket endpoint is running. Now Connect to /chat";
    }
}
