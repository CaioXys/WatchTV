package com.example.watch.Watch;


import com.example.watch.Watch.main.PrincipalConsole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication implements CommandLineRunner {
    private final PrincipalConsole principalConsole;

    public static void main(String[] args) {
        SpringApplication.run(WatchApplication.class, args);
    }

    public WatchApplication(PrincipalConsole principalConsole) {
        this.principalConsole = principalConsole;
    }

    @Override
    public void run(String... args) throws Exception {
        principalConsole.escolhaNumero();
    }
}
