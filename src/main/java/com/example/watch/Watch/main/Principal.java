package com.example.watch.Watch.main;

import com.example.watch.Watch.model.DadosFilmes;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "http://www.omdbapi.com/";
    private final String APIKEY = "?apikey=2f5aff76";

    public void exibeMenu() {
        System.out.println("Digite o nome do Filme: ");
        var titulo = scanner.nextLine();
        var json = apiService.obterDados(ENDERECO + titulo.replace(" ","+") + APIKEY);
        DadosFilmes dadosFilmes = conversor.obterDados(json, DadosFilmes.class);
        System.out.println(dadosFilmes);
    }

    List<DadosFilmes> dados = new ArrayList<>();

}
