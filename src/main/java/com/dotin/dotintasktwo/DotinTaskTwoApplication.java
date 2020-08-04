package com.dotin.dotintasktwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DotinTaskTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DotinTaskTwoApplication.class, args);
    }

}
