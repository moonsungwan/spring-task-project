package com.task.sample.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.sample.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            ChatMessage chatMessage = mapper.readValue(message.getBody(), ChatMessage.class);
            messageList.add(message.toString());

            log.info("받은 메시지 {}, " + message.toString());
            log.info("chatMessage.getSender() = {}", chatMessage.getSender());
            log.info("chatMessage.getContext() = {}", chatMessage.getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}