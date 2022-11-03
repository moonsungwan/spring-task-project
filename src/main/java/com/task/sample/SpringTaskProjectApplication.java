package com.task.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class SpringTaskProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskProjectApplication.class, args);
    }

}
