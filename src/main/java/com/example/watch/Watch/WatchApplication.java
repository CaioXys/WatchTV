package com.example.watch.Watch;


import com.example.watch.Watch.main.PrincipalEscolha;
import com.example.watch.Watch.main.PrincipalFilme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication implements CommandLineRunner {
    private final PrincipalEscolha principalEscolha;

    public static void main(String[] args) {
        SpringApplication.run(WatchApplication.class, args);
    }

    public WatchApplication(PrincipalEscolha principalEscolha) {
        this.principalEscolha = principalEscolha;
    }

    @Override
    public void run(String... args) throws Exception {
        principalEscolha.escolhaNumero();
    }
}
