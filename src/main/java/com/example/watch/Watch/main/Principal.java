package com.example.watch.Watch.main;

import com.example.watch.Watch.model.DadosFilmes;
import com.example.watch.Watch.model.Genero;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConsultaGemini;
import com.example.watch.Watch.services.ConverteDados;

import java.util.Scanner;

public class Principal {
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

        String generoIngles = dadosFilmes.genero().split(",")[0].trim(); // .split(",")[0].trim() pega o primeiro
        Genero genero = Genero.fromApi(generoIngles);

        if (dadosFilmes.titulo() != null) {
            System.out.println("------------------------------");
            System.out.println("Título: " + dadosFilmes.titulo());
            System.out.println("Gênero: " + genero.getGeneroPtBr());
            System.out.println("Data de lançamento: " + dadosFilmes.lancamento());
            System.out.println("Duração: " + dadosFilmes.duracao());
            System.out.println("Avaliação: " + dadosFilmes.avaliacao());
            System.out.println("Sinopse: " + ConsultaGemini.obterTraducao(dadosFilmes.sinopse()).trim());
            System.out.println("------------------------------");
        } else {
            System.out.println("Esse filme não pode ser encontrado");
        }

        System.out.println("Quer procurar outro filme (S/N)?");
        String resp = scanner.nextLine();

        if (resp.equals("S")) {
            exibeMenu();
        } else if (resp.equals("N")){
            System.out.println("Saindo!");
        } else {
            verificaResp();
        }
        scanner.close();
    }

    public void verificaResp() {
        System.out.println("Quer procurar outro filme (S/N)?");
        String resp = scanner.nextLine();

        if (!resp.equals("S") && !resp.equals("N")) {
            exibeMenu();
        }
    }
}
