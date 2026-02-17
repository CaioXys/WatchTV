package com.example.watch.Watch.main;

import com.example.watch.Watch.model.DadosFilmes;
import com.example.watch.Watch.model.Filme;
import com.example.watch.Watch.model.Genero;
import com.example.watch.Watch.model.Lista;
import com.example.watch.Watch.repository.ListaRepository;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalSerie {
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();
    private final String OMDBAPIKEY = System.getenv("APIKEY_OMDB");
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API = "&apikey=" + OMDBAPIKEY + "&type=series";
    private final ListaRepository listaRepository;

    public PrincipalSerie(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public void exibeMenu() {
        System.out.print("Digite o nome da série: ");
        var titulo = scanner.nextLine();
        var json = apiService.obterDados(ENDERECO + titulo.replace(" ", "+") + API);
        DadosFilmes dadosSeries = conversor.obterDados(json, DadosFilmes.class);

        String generoIngles = dadosSeries.genero().split(",")[0].trim(); // .split(",")[0].trim() pega o primeiro
        Genero genero = Genero.fromApi(generoIngles);

            if (dadosSeries.titulo() != null) {
                System.out.println("------------------------------");
                System.out.println("Título: " + dadosSeries.titulo());
                System.out.println("Gênero: " + genero.getGeneroPtBr());
                System.out.println("Data de lançamento: " + dadosSeries.lancamento());
                System.out.println("Duração: " + dadosSeries.duracao());
                System.out.println("Avaliação: " + dadosSeries.avaliacao());
//                System.out.println("Sinopse: " + ConsultaGemini.obterTraducao(dadosFilmes.sinopse()).trim());
                System.out.println("------------------------------");
            } else if (dadosSeries.resposta().equals("False")) {
                System.out.println("Esse filme não pode ser encontrado");
            }
        Filme filme = new Filme(dadosSeries);
        filme.setTitulo(dadosSeries.titulo());
        filme.setGenero(genero.getGeneroPtBr());
        escolhaLista(filme);
    }

    public void verificaResp() {
        System.out.println("\nQuer procurar outro filme (S/N)?");
        String resp = scanner.nextLine();
        if (resp.equals("S")) {
            exibeMenu();
        } else if (resp.equals("N")){
            System.out.println("Saindo!");
        } else {
            verificaResp();
        }
    }

    public void escolhaLista(Filme filme) {
        System.out.println("\nVocê quer adicionar esse filme a sua lista? (S/N)");
        String resp = scanner.nextLine();
        if (resp.equals("S")) {
            Lista lista = new Lista(filme);
            listaRepository.save(lista);
            System.out.println("Filme adicionado na lista!");
            verificaResp();
        } else if (resp.equals("N")) {
            System.out.println("O filme não foi adicionado!");
            verificaResp();
        } else {
            escolhaLista(filme);
        }
    }
}
