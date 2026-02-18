package com.example.watch.Watch.main;

import com.example.watch.Watch.model.*;
import com.example.watch.Watch.repository.ListaRepository;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalSerie {
    private final PrincipalEscolha principalEscolha;
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();
    private final String OMDBAPIKEY = System.getenv("APIKEY_OMDB");
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API = "&apikey=" + OMDBAPIKEY + "&type=series";
    private final ListaRepository listaRepository;

    public PrincipalSerie(ListaRepository listaRepository, PrincipalEscolha principalEscolha) {
        this.listaRepository = listaRepository;
        this.principalEscolha = principalEscolha;
    }

    public void exibeMenu() {
        System.out.print("Digite o nome da série: ");
        var titulo = scanner.nextLine();
        var json = apiService.obterDados(ENDERECO + titulo.replace(" ", "+") + API);
        DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);

        String generoIngles = dadosSeries.genero().split(",")[0].trim(); // .split(",")[0].trim() pega o primeiro
        Genero genero = Genero.fromApi(generoIngles);

            if (dadosSeries.titulo() != null) {
                System.out.println("------------------------------");
                System.out.println("Título: " + dadosSeries.titulo());
                System.out.println("Gênero: " + genero.getGeneroPtBr());
                System.out.println("Data de lançamento: " + dadosSeries.lancamento());
                System.out.println("Duração: " + dadosSeries.duracao());
                System.out.println("Avaliação: " + dadosSeries.avaliacao());
//                System.out.println("Sinopse: " + ConsultaGemini.obterTraducao(dadosSeries.sinopse()).trim());
                System.out.println("------------------------------");
            } else if (dadosSeries.resposta().equals("False")) {
                System.out.println("Essa série não pode ser encontrada");
            }
        Serie serie = new Serie(dadosSeries);
        serie.setTitulo(dadosSeries.titulo());
        serie.setGenero(genero.getGeneroPtBr());
        escolhaLista(serie);
    }

    public void verificaResp() {
        System.out.println("\nQuer procurar outra série? (S/N)");
        String resp = scanner.nextLine();
        if (resp.equals("S")) {
            exibeMenu();
        } else if (resp.equals("N")){
            System.out.println("Saindo!");
            principalEscolha.escolhaNumero();
        } else {
            verificaResp();
        }
    }

    public void escolhaLista(Serie serie) {
        System.out.println("\nVocê quer adicionar essa série a sua lista? (S/N)");
        String resp = scanner.nextLine();
        if (resp.equals("S")) {
            Lista lista = new Lista(serie);
            listaRepository.save(lista);
            System.out.println("Série adicionado na lista!");
            verificaResp();
        } else if (resp.equals("N")) {
            System.out.println("A série não foi adicionado!");
            verificaResp();
        } else {
            escolhaLista(serie);
        }
    }
}
