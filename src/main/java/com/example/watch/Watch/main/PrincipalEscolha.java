package com.example.watch.Watch.main;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PrincipalEscolha {
    private final PrincipalFilme principalFilme;
    private final PrincipalSerie principalSerie;
    private Scanner scanner = new Scanner(System.in);

    public PrincipalEscolha(PrincipalFilme principalFilme, PrincipalSerie principalSerie) {
        this.principalFilme = principalFilme;
        this.principalSerie = principalSerie;
    }

    public void escolhaNumero() {
        System.out.println("---------------------");
        System.out.println("1 - Buscar filmes");
        System.out.println("2 - Buscar séries");
        System.out.println("3 - Mostrar lista (Opção não funcionando ainda)");
        System.out.println("4 - Sair");
        System.out.print("Digite um número para busca: ");
        System.out.println("\n---------------------");
        var resp = scanner.nextInt();

        switch (resp) {
            case 1:
                principalFilme.exibeMenu();
                break;
            case 2:
                principalSerie.exibeMenu();
                break;
            default:
                break;
        }
    }

}
