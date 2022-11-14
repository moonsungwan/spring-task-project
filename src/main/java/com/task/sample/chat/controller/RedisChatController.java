package com.task.sample.chat.controller;

import com.task.sample.chat.dto.ChatMessage;
import com.task.sample.chat.service.RedisPubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisChatController {

    private final RedisPubService redisPublisher;

    @PostMapping("/api/v1/chat")
    public String sendMessage(@RequestBody ChatMessage chatMessage) {
        redisPublisher.sendMessage(chatMessage);

        return "success";
    }
}