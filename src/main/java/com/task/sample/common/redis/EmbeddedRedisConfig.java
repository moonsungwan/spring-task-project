package com.task.sample.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.util.Optional;

@Slf4j
@Profile("local")
@Configuration
public class EmbeddedRedisConfig implements InitializingBean, DisposableBean {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @Override
    public void destroy() throws Exception {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redisServer = RedisServer.builder()
                                 .port(redisPort)
                                 .setting("maxmemory 128M") //maxheap 128M
                                 .build();

        redisServer.start();
    }

}