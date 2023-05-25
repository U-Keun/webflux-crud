package com.ukeun.webfluxcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EntityScan("com.ukeun.webfluxcrud.entity")
@EnableR2dbcRepositories("com.ukeun.webfluxcrud.repository")
public class WebfluxCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxCrudApplication.class, args);
    }
}
