package com.example.watch.Watch.model;

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            this.lancamento = LocalDate.parse(dadosSeries.lancamento(), formatter);
        } catch (DateTimeException ex) {
            this.lancamento = null;
        }
    }
}
