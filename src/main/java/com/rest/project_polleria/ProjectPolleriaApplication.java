package com.rest.project_polleria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.rest.project_polleria.repository")
public class ProjectPolleriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPolleriaApplication.class, args);
    }

}
