package com.example.watch.Watch.services;

import com.example.watch.Watch.model.Lista;
import com.example.watch.Watch.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;
    Scanner scanner = new Scanner(System.in);

    public void exibeLista() {
        boolean exit = true;
        while (exit) {
            List<Lista> favoritos = listaRepository.findAll();

            if (favoritos.isEmpty()) {
                System.out.println("Sua lista está vazia!");
                return;
            }

            favoritos.forEach(f -> {
                System.out.println("Título: " + f.getTitulo());
                System.out.println("Gênero: " + f.getGenero());
                System.out.println("Tipo: " + f.getTipo());
                System.out.println("--------------------");
            });
            exit = verificaLista();
        }
    }

    public boolean verificaLista() {
        while (true) {
            System.out.println("Voltar para o menu? (S)");
            var resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) return false;
            System.out.println("Opção inválida");
        }
    }
}
