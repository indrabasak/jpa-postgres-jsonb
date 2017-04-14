package com.basaki.example.postgres.jsonb.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * {@code BookApplication} represents the entry point for book controller
 * spring boot application.
 * <p/>
 *
 * @author Indra Basak
 * @since 2/23/17
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.basaki.example.postgres.jsonb.config",
        "com.basaki.example.postgres.jsonb.controller",
        "com.basaki.example.postgres.jsonb.data.entity",
        "com.basaki.example.postgres.jsonb.data.repository",
        "com.basaki.example.postgres.jsonb.error",
        "com.basaki.example.postgres.spring.jsonb.model",
        "com.basaki.example.postgres.jsonb.service"})
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
