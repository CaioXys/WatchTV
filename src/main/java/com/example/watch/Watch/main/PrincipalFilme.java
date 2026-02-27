package com.example.watch.Watch.main;

import com.example.watch.Watch.model.*;
import com.example.watch.Watch.repository.ListaRepository;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConsultaGemini;
import com.example.watch.Watch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalFilme {
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();
    private ConverteDados conversor = new ConverteDados();
    private final String OMDBAPIKEY = System.getenv("APIKEY_OMDB");
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API = "&apikey=" + OMDBAPIKEY + "&type=movie";
    private final ListaRepository listaRepository;
    public PrincipalFilme(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public void exibeMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.print(">> Digite o nome do Filme: ");
            var titulo = scanner.nextLine();
            var json = apiService.obterDados(ENDERECO + titulo.replace(" ", "+") + API);
            DadosFilmes dadosFilmes = conversor.obterDados(json, DadosFilmes.class);

            String generoIngles = dadosFilmes.genero().split(",")[0].trim(); // .split(",")[0].trim() pega o primeiro
            Genero genero = Genero.fromApi(generoIngles);
            Tipo tipo = Tipo.fromApi(dadosFilmes.tipo());

            if (dadosFilmes.titulo() != null) {
                System.out.println("------------------------------");
                System.out.println("-> Título: " + dadosFilmes.titulo());
                System.out.println("-> Gênero: " + genero.getGeneroPtBr());
                System.out.println("-> Data de lançamento: " + dadosFilmes.lancamento());
                System.out.println("-> Duração: " + dadosFilmes.duracao());
                System.out.println("-> Avaliação: " + dadosFilmes.avaliacao());
//                System.out.println("Sinopse: " + ConsultaGemini.obterTraducao(dadosFilmes.sinopse()).trim());
                System.out.println("------------------------------");
            } else if (dadosFilmes.resposta().equals("False")) {
                System.out.println("--Esse filme não pode ser encontrado.--");
            }
            Filme filme = new Filme(dadosFilmes);
            filme.setTitulo(dadosFilmes.titulo());
            filme.setTipo(tipo.getTipoPtBr());
            filme.setGenero(genero.getGeneroPtBr());
            escolhaLista(filme);
            continuar = verificaResp();
        }
    }

    public boolean verificaResp() {
        while (true) {
            System.out.println("\n>> Quer procurar outro filme (S/N)?");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) return true;
            if (resp.equalsIgnoreCase("N")) return false;
            System.out.println("--Opção inválida!--");
        }
    }

    public void escolhaLista(Filme filme) {
        while (true) {
            System.out.println("\n>> Você quer adicionar esse filme à sua lista (S/N)?");
            String resp = scanner.nextLine();
            if (!listaRepository.existsByTitulo(filme.getTitulo())) {
                if (resp.equalsIgnoreCase("S")) {
                    listaRepository.save(new Lista(filme));
                    System.out.println("\n--Filme adicionado!--");
                    return;
                } else if (resp.equalsIgnoreCase("N")) {
                    System.out.println("\n--O filme não foi adicionado!--");
                    return;
                } else {
                    System.out.println("\n--Opção inválida!--");
                }
            } else {
                System.out.println("--Esse filme já foi adicionado!--");
                return;
            }
        }
    }
}
