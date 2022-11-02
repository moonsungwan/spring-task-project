package com.task.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringTaskProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskProjectApplication.class, args);
    }

}
