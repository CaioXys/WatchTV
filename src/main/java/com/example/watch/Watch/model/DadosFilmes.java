package com.example.watch.Watch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilmes(@JsonAlias("Title") String titulo,
                          @JsonAlias("Year") String ano,
                          @JsonAlias("Genre") String genero,
                          @JsonAlias("Released") String lancamento,
                          @JsonAlias("Runtime") String duracao,
                          @JsonAlias("imdbRating") String avaliacao) {
}
