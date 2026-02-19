package com.example.watch.Watch.main;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalConsole {
    private final PrincipalFilme principalFilme;
    private final PrincipalSerie principalSerie;
    private Scanner scanner = new Scanner(System.in);

    public PrincipalConsole(PrincipalFilme principalFilme, PrincipalSerie principalSerie) {
        this.principalFilme = principalFilme;
        this.principalSerie = principalSerie;
    }

    public void escolhaNumero() {
        while (true) {
            System.out.println("\n----------------------------");
            System.out.println("1 - Buscar filmes");
            System.out.println("2 - Buscar séries");
            System.out.println("3 - Mostrar lista (Opção não funcionando ainda)");
            System.out.println("4 - Sair");
            System.out.println("----------------------------");
            System.out.print("Digite um número para busca: ");
            var resp = Integer.parseInt(scanner.nextLine());

            switch (resp) {
                case 1:
                    principalFilme.exibeMenu();
                    break;
                case 2:
                    principalSerie.exibeMenu();
                    break;
                case 3:
                    System.out.println("Ainda não implementado");
                    break;
                case 4:
                    System.out.println("Saindo");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
