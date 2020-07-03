package com.example.demo.client.resttemplate;

import com.example.demo.client.commons.BaseCLI;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URI;

@Component
public class CLI extends BaseCLI {

    private final RestTemplate restTemplate;

    public CLI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendFile(File file) {
        sendFileAsOctetStream(file);
    }

    private void sendFileAsMultiPart(File file) {
        final MultiValueMap<String, HttpEntity<?>> body = createMultiPart(file);

        final RequestEntity<MultiValueMap<String, HttpEntity<?>>> request = RequestEntity.post(URI.create("http://localhost:8080/file"))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body);

        restTemplate.exchange(request, void.class);
    }

    private void sendFileAsOctetStream(File file) {
        final RequestEntity<FileSystemResource> request = RequestEntity.post(URI.create("http://localhost:8080/file"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));

        restTemplate.exchange(request, void.class);
    }
}
