package com.example.watch.Watch.main;

import com.example.watch.Watch.services.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalConsole {
    private final PrincipalFilme principalFilme;
    private final PrincipalSerie principalSerie;
    @Autowired
    private ListaService listaService;
    private Scanner scanner = new Scanner(System.in);

    public PrincipalConsole(PrincipalFilme principalFilme, PrincipalSerie principalSerie) {
        this.principalFilme = principalFilme;
        this.principalSerie = principalSerie;
    }

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void escolhaNumero() {
        while (true) {
            String menu = """
            \n----------------------------
            1🎥 - Buscar filmes
            2📺 - Buscar séries
            3🧾 - Mostrar lista de favoritos
            4❌ - Sair
            ----------------------------
            """;
            System.out.println(menu);
            System.out.print(">> Digite um número para busca: ");
            var entrada = scanner.nextLine();

            if (!entrada.matches("\\d+")) { // Verifica se a entrada não é um número antes de converter
                System.out.println(">> Apenas dígitos numéricos!");
                delay(1000);
                continue;
            }
            int resp = Integer.parseInt(entrada);

            switch (resp) {
                case 1:
                    principalFilme.exibeMenu();
                    break;
                case 2:
                    principalSerie.exibeMenu();
                    break;
                case 3:
                    listaService.exibeLista();
                    break;
                case 4:
                    System.out.println("\n--Saindo--");
                    return;
                default:
                    System.out.println("\n--Opção inválida!--");
                    break;
            }
        }
    }
}
