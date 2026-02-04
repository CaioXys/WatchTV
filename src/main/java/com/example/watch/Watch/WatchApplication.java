package com.example.watch.Watch;


import com.example.watch.Watch.main.Principal;
import com.example.watch.Watch.services.ApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WatchApplication.class, args);
	}

		@Override
		public void run(String... args) throws Exception {
			Principal principal = new Principal();
			principal.exibeMenu();
		}


}
