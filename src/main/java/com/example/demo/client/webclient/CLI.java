package com.example.demo.client.webclient;

import com.example.demo.client.commons.BaseCLI;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;

@Component
public class CLI extends BaseCLI {

    private final WebClient webClient;

    public CLI(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendFile(File file) {
        sendFileAsOctetStream(file);
    }

    private void sendFileAsMultiPart(File file) {
        final MultiValueMap<String, HttpEntity<?>> body = createMultiPart(file);

        webClient.post()
                .uri("/file")
                .body(BodyInserters.fromMultipartData(body))
                .exchange()
                .block();
    }

    private void sendFileAsOctetStream(File file) {
        webClient.post()
                .uri("/file")
                .body(BodyInserters.fromResource(new FileSystemResource(file)))
                .exchange()
                .block();
    }
}
