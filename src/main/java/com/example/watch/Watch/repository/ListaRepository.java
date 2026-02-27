package com.example.watch.Watch.repository;

import com.example.watch.Watch.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    void deleteByTitulo(String titulo);
    boolean existsByTitulo(String titulo);

}
