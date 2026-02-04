package com.example.watch.Watch.services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
