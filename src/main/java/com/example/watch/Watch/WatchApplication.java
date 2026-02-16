package com.example.watch.Watch;


import com.example.watch.Watch.main.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication implements CommandLineRunner {
    private final Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(WatchApplication.class, args);
    }

    public WatchApplication(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void run(String... args) throws Exception {
        principal.exibeMenu();
    }
}
