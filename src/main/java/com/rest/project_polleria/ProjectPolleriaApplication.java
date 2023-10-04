package com.rest.project_polleria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjectPolleriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPolleriaApplication.class, args);
    }

}
