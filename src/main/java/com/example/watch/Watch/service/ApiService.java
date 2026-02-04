package com.example.watch.Watch.service;

import com.example.watch.Watch.dto.OmdBDTO;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ApiService {

    public OmdBDTO omdB(String title) throws IOException, InterruptedException {

        System.out.println("Digite o título: ");
        var scanner = new Scanner(System.in);
        title = scanner.nextLine();
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

        var apikey = "2f5aff76";

        OmdBDTO omdBDTO = new OmdBDTO();

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://www.omdbapi.com/?apikey="+ apikey + "&"+ encodedTitle)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            omdBDTO = mapper.readValue(response.body(), OmdBDTO.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return omdBDTO;
    }

}
