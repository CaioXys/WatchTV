package com.example.watch.Watch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lista_favoritos")
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;

    public Lista(){}

    public Lista(Filme filme) {
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
    }
}
