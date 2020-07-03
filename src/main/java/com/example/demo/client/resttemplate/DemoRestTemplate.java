package com.example.demo.client.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.example.demo.client.resttemplate")
public class DemoRestTemplate {
    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(DemoRestTemplate.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Bean
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
