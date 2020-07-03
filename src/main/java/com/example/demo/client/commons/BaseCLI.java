package com.example.demo.client.commons;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.Scanner;

public abstract class BaseCLI implements CommandLineRunner {

    @Override
    public void run(String... args) {
        final Scanner scanner = new Scanner(System.in);
        String token;
        do {
            System.out.println("Type absolute path to file which should be sent to the server.");
            token = scanner.nextLine();
            if ("quit".equals(token)) {
                break;
            }

            File file;
            try {
                file = new File(token);
                if (!file.exists()) {
                    System.out.println("File does not exist: " + token);
                    continue;
                }
                if (!file.canRead()) {
                    System.out.println("File can not be read: " + token);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            sendFile(file);
        } while (true);
    }

    public abstract void sendFile(File file);

    protected MultiValueMap<String, HttpEntity<?>> createMultiPart(File file) {
        final MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("file", new FileSystemResource(file), MediaType.APPLICATION_OCTET_STREAM).filename(file.getName());
        return multipartBodyBuilder.build();
    }
}
