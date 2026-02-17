package com.example.watch.Watch;


import com.example.watch.Watch.main.PrincipalFilme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication implements CommandLineRunner {
    private final PrincipalFilme principalFilme;

    public static void main(String[] args) {
        SpringApplication.run(WatchApplication.class, args);
    }

    public WatchApplication(PrincipalFilme principalFilme) {
        this.principalFilme = principalFilme;
    }

    @Override
    public void run(String... args) throws Exception {
        principalFilme.exibeMenu();
    }
}
