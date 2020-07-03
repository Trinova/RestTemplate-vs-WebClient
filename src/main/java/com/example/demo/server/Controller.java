package com.example.demo.server;

import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.*;

@RestController
public class Controller {

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        final File restTemplateTest = File.createTempFile("restTemplateTest", ".multipart");
        try (OutputStream outputStream = new FileOutputStream(restTemplateTest); InputStream inputStream = file.getInputStream()) {
            StreamUtils.copy(inputStream, outputStream);
        }
    }

    @PostMapping(value = "/file", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void uploadFile(ServletRequest request) throws IOException {
        final File restTemplateTest = File.createTempFile("restTemplateTest", ".octetstream");
        try (OutputStream outputStream = new FileOutputStream(restTemplateTest); InputStream inputStream = request.getInputStream()) {
            StreamUtils.copy(inputStream, outputStream);
        }
    }
}
