package com.example.watch.Watch.main;

import com.example.watch.Watch.model.DadosFilmes;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConverteDados;

import java.util.Scanner;

public class Principal {

    private String resp;

    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();

    private final String OMDBAPIKEY = System.getenv("APIKEY_OMDB");
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API = "&apikey=" + OMDBAPIKEY;

    public void exibeMenu() {
        System.out.print("Digite o nome do Filme: ");
        var titulo = scanner.nextLine();
        var json = apiService.obterDados(ENDERECO + titulo.replace(" ", "+") + API);
        DadosFilmes dadosFilmes = conversor.obterDados(json, DadosFilmes.class);
        System.out.println(dadosFilmes);
        System.out.println("Quer procurar outro filme (S/N)?");
        resp = scanner.nextLine();

        if (resp.equals("S")) {
            exibeMenu();
        } else {
            System.out.println("Saindo!");
        }
    }
}
