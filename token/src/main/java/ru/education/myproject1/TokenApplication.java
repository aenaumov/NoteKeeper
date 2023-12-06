package ru.education.myproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TokenApplication {


    public static void main(String[] args) {
//        SpringApplication.run(TokenApplication.class, args);
        SpringApplication application = new SpringApplication(TokenApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

}