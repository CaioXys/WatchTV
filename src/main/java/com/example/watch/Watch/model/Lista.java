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
    private String tipo;

    public Lista(){}

    public Lista(Filme filme) {
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
        this.tipo = filme.getTipo();
    }

    public Lista(Serie serie) {
        this.titulo = serie.getTitulo();
        this.genero = serie.getGenero();
        this.tipo = serie.getTipo();
    }
}
