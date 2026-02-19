package com.example.watch.Watch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.DateTimeException;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Serie {
    private String titulo;
    private String genero;
    private Integer temporadas;
    private LocalDate lancamento;
    private Double avaliacao;
    private String tipo;

    public Serie(DadosSeries dadosSeries) {
        this.titulo = dadosSeries.titulo();

        try {
            this.avaliacao = Double.valueOf(dadosSeries.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }

        try {
            this.lancamento = LocalDate.parse(dadosSeries.lancamento());
        } catch (DateTimeException ex) {
            this.lancamento = null;
        }
    }
}
