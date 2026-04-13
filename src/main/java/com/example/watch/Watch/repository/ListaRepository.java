package com.example.watch.Watch.repository;

import com.example.watch.Watch.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    @Transactional
    void deleteByTitulo(String titulo);
    boolean existsByTitulo(String titulo);

}
