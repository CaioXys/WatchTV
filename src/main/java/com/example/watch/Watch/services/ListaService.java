package com.example.watch.Watch.services;

import com.example.watch.Watch.model.Lista;
import com.example.watch.Watch.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;
    Scanner scanner = new Scanner(System.in);

    @Transactional
    public void exibeLista() {
        boolean exit = true;
        boolean exitTwo = true;
        while (exit && exitTwo) {
            List<Lista> favoritos = listaRepository.findAll();

            if (favoritos.isEmpty()) {
                System.out.println("\n--Sua lista está vazia!--");
                return;
            }

            favoritos.forEach(f -> {
                System.out.println("----------------------------");
                System.out.println("-> Título: " + f.getTitulo());
                System.out.println("-> Gênero: " + f.getGenero());
                System.out.println("-> Tipo: " + f.getTipo());
            });
            exitTwo = excluirFavorito();
            exit = verificaLista();
        }
    }

    public boolean excluirFavorito() {
        while(true) {
            List<Lista> favoritos = listaRepository.findAll();
            if (!favoritos.isEmpty()) {
                System.out.println("\n>> Você quer excluir algum filme ou série da lista (S/N)?");
                var resp = scanner.nextLine();
                if (resp.equalsIgnoreCase("S")) {
                    System.out.print("\n>> Digite o nome: ");
                    var respName = scanner.nextLine();
                    listaRepository.deleteByTitulo(respName);
                    System.out.println("\n--Filme removido!--");
                } else if (resp.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    System.out.println("\n--Opção inválida!--");
                }
            } else {
                System.out.println("\n--Sua lista está vazia!--");
                return false;
            }
        }
    }

    public boolean verificaLista() {
        while (true) {
            System.out.println("\n>> Voltar para o menu? (S)");
            var resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) return false;
            System.out.println("\n--Opção inválida!--");
        }
    }
}
