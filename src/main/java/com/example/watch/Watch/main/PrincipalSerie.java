package com.example.watch.Watch.main;

import com.example.watch.Watch.model.*;
import com.example.watch.Watch.repository.ListaRepository;
import com.example.watch.Watch.services.ApiService;
import com.example.watch.Watch.services.ConsultaGemini;
import com.example.watch.Watch.services.ConverteDados;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
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
        boolean continuar = true;
        while (continuar) {
            System.out.print(">> Digite o nome da série: ");
            var titulo = scanner.nextLine();
            var json = apiService.obterDados(ENDERECO + titulo.replace(" ", "+") + API);
            DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);

            String generoIngles = dadosSeries.genero().split(",")[0].trim(); // .split(",")[0].trim() pega o primeiro
            Genero genero = Genero.fromApi(generoIngles);
            Tipo tipo = Tipo.fromApi(dadosSeries.tipo());

            if (dadosSeries.titulo() != null) {
                Serie serie = new Serie(dadosSeries);
                String data = dadosSeries.lancamento() != null
                        ? serie.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "Data indisponível.";

                System.out.println("\n------------------------------");
                System.out.println("-> Título: " + dadosSeries.titulo());
                System.out.println("-> Gênero: " + genero.getGeneroPtBr());
                System.out.println("-> Data de lançamento: " + data);
                System.out.println("-> Temporadas: " + dadosSeries.temporadas());
                System.out.println("-> Avaliação: " + dadosSeries.avaliacao());
                System.out.println("-> Sinopse: " + ConsultaGemini.obterTraducao(dadosSeries.sinopse()).trim());
                System.out.println("------------------------------");
            } else if (dadosSeries.resposta().equals("False")) {
                System.out.println("\n--Essa série não pode ser encontrada.--");
            }
            Serie serie = new Serie(dadosSeries);
            serie.setTitulo(dadosSeries.titulo());
            serie.setTipo(tipo.getTipoPtBr());
            serie.setGenero(genero.getGeneroPtBr());
            escolhaLista(serie);
            continuar = verificaResp();
        }
    }

    public boolean verificaResp() {
        while (true) {
            System.out.println("\n>> Quer procurar outra série? (S/N)");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) return true;
            if (resp.equalsIgnoreCase("N")) return false;
            System.out.println("\n--Opção inválida!--");
        }
    }

    public void escolhaLista(Serie serie) {
        while (true) {
            System.out.println("\n>> Você quer adicionar essa série à sua lista? (S/N)");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                listaRepository.save(new Lista(serie));
                System.out.println("--Série adicionada!--");
                return;
            } else if (resp.equalsIgnoreCase("N")) {
                System.out.println("\n--A série não foi adicionada!--");
                return;
            } else {
                System.out.println("\n--Opção inválida!--");
            }
        }
    }
}
