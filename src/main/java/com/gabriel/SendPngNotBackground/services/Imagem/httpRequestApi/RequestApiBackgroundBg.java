package com.gabriel.SendPngNotBackground.services.Imagem.httpRequestApi;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RequestApiBackgroundBg {
    public static ResponseEntity<String> Request(String apiKey, Path arquivo, String imageType) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.remove.bg/v1.0/removebg";

        byte[] imageBytes = Files.readAllBytes(arquivo);
        org.springframework.core.io.ByteArrayResource imageResource = new org.springframework.core.io.ByteArrayResource(imageBytes) {
            @Override
            public String getFilename() {
                return arquivo.getFileName().toString();
            }
        };

        HttpHeaders fileHeaders = new HttpHeaders();
        if (imageType.equals("png")) {
            fileHeaders.setContentType(MediaType.IMAGE_PNG);
        } else if (imageType.equals("jpg")) {
            fileHeaders.setContentType(MediaType.IMAGE_JPEG);
        }
        HttpEntity<org.springframework.core.io.ByteArrayResource> fileEntity = new HttpEntity<>(imageResource, fileHeaders);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image_file", fileEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("X-Api-Key", apiKey);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.postForEntity(url, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println("API error: " + e.getResponseBodyAsString());
            throw e;
        }
    }
}
