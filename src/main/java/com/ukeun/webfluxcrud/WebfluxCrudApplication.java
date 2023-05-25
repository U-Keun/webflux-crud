package com.ukeun.webfluxcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan({"com.ukeun.webfluxcrud.router", "com.ukeun.webfluxcrud.handler",
        "com.ukeun.webfluxcrud.service", "com.ukeun.webfluxcrud.repository"})
@EntityScan("com.ukeun.webfluxcrud.entity")
@EnableR2dbcRepositories("com.ukeun.webfluxcrud.repository")
public class WebfluxCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxCrudApplication.class, args);
    }
}
