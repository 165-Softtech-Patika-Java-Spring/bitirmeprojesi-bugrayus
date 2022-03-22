package com.bitirmeprojesibugrayus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BitirmeprojesiBugrayusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitirmeprojesiBugrayusApplication.class, args);
    }
}
