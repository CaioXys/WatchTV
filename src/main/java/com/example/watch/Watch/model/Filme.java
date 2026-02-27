package com.example.watch.Watch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@ToString
public class Filme {
    private String titulo;
    private String genero;
    private LocalDate lancamento;
    private String duracao;
    private Double avaliacao;
    private String tipo;

    public Filme(DadosFilmes dadosFilmes) {
        this.titulo = dadosFilmes.titulo();

        try {
            this.avaliacao = Double.valueOf(dadosFilmes.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            this.lancamento = LocalDate.parse(dadosFilmes.lancamento(), formatter);
        } catch (DateTimeException ex) {
            this.lancamento = null;
        }
    }
}
