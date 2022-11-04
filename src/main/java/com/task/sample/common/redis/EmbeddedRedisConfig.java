package com.task.sample.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    public void startRedis() throws IOException, URISyntaxException {
        redisServer = RedisServer.builder()
                                 .port(redisPort)
                                 .setting("maxmemory 128M") //maxheap 128M
                                 .build();

        redisServer.start();
    }

    public void stopRedis() throws InterruptedException {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

}