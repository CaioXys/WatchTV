package com.example.watch.Watch.model;

import lombok.Getter;

import java.util.Arrays;

public enum Genero {

    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    ANIMACAO("Animation", "Animação"),
    TERROR("Horror", "Terror"),
    OUTRO("Outro", "Outro");

    private String generoOmdB;
    @Getter
    private String generoPtBr;


    Genero(String generoOmdB, String generoPtBr) {
        this.generoOmdB = generoOmdB;
        this.generoPtBr = generoPtBr;
    }

    public static Genero fromApi(String valor) {
        if (valor == null) return OUTRO;

        return Arrays.stream(values())
                .filter(g -> g.generoOmdB.equalsIgnoreCase(valor.trim()))
                .findFirst()
                .orElse(OUTRO);
    }
}
