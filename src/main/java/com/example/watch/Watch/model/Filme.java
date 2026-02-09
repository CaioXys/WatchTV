package com.example.watch.Watch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DateTimeException;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Filme {

    private String titulo;
    private String genero;
    private LocalDate lancamento;
    private String duracao;
    private Double avaliacao;

    public Filme(DadosFilmes dadosFilmes) {
        this.titulo = dadosFilmes.titulo();

        try {
            this.avaliacao = Double.valueOf(dadosFilmes.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }

        try {
            this.lancamento = LocalDate.parse(dadosFilmes.lancamento());
        } catch (DateTimeException ex) {
            this.lancamento = null;
        }
    }
}
