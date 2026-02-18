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
public class PrincipalFilme {
    private final PrincipalEscolha principalEscolha;
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();
    private final String OMDBAPIKEY = System.getenv("q");
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API = "&apikey=" + OMDBAPIKEY + "&type=movie";
    private final ListaRepository listaRepository;

    public PrincipalFilme(ListaRepository listaRepository, PrincipalEscolha principalEscolha) {
        this.listaRepository = listaRepository;
        this.principalEscolha = principalEscolha;
    }

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
//                System.out.println("Sinopse: " + ConsultaGemini.obterTraducao(dadosFilmes.sinopse()).trim());
                System.out.println("------------------------------");
            } else if (dadosFilmes.resposta().equals("False")) {
                System.out.println("Esse filme não pode ser encontrado");
            }
        Filme filme = new Filme(dadosFilmes);
        filme.setTitulo(dadosFilmes.titulo());
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
            principalEscolha.escolhaNumero();
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
