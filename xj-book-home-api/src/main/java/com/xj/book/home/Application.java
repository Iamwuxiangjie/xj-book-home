package com.xj.book.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackages={"com.xj.book.home.model"})
@EnableJpaRepositories(basePackages={"com.xj.book.home.dao.mysql"})
@EnableMongoRepositories(basePackages={"com.xj.book.home.dao.mongo"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
