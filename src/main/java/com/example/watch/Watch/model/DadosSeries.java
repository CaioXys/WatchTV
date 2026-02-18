package com.example.watch.Watch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSeries(@JsonAlias("Title") String titulo,
                          @JsonAlias("Genre") String genero,
                          @JsonAlias("Released") String lancamento,
                          @JsonAlias("Runtime") String duracao,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonAlias("Plot") String sinopse,
                          @JsonAlias("totalSeasons") String temporadas,
                          @JsonAlias("Type") String tipo,
                          @JsonAlias("Response") String resposta) {
}
