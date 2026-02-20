package com.example.watch.Watch.model;

import lombok.Getter;

import java.util.Arrays;

public enum Tipo {

    FILME("movie", "Filme"),
    SERIE("series", "Série");

    private String tipoOg;
    @Getter
    private String tipoPtBr;

    Tipo(String tipoOg, String tipoPtBr) {
        this.tipoOg = tipoOg;
        this.tipoPtBr = tipoPtBr;
    }

    public static Tipo fromApi(String tipo) {
        return Arrays.stream(Tipo.values())
                .filter(t -> t.tipoOg
                        .equalsIgnoreCase(tipo.trim()))
                .findFirst()
                .orElse(null);
    }
}
