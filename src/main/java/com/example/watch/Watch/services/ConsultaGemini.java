package com.example.watch.Watch.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    public static String obterTraducao(String text) {

        Client client = new Client();

        GenerateContentConfig config = GenerateContentConfig.builder()
                .temperature(0.0F)
                .maxOutputTokens(1000)
                .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash-lite",
                        "Você é um tradutor técnico e literal." +
                                "Traduza o texto para português brasileiro. " +
                                "NÃO adicione introduções como 'Aqui está', NÃO dê opções. " +
                                "Retorne APENAS o texto traduzido, nada mais." + text,
                        config);

        return response.text();
    }
}
