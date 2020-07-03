package com.example.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.demo.server")
public class DemoServer {

	public static void main(String[] args) {
		SpringApplication.run(DemoServer.class, args);
	}

}
