package com.week.zumgnmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZumgnmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZumgnmarketApplication.class, args);
    }

}
