package com.example.watch.Watch;

import com.example.watch.Watch.dto.OmdBDTO;
import com.example.watch.Watch.service.ApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchApplication.class, args);

		ApiService apiService = new ApiService();
		try {
			OmdBDTO omdBDTO = apiService.omdB("");
			System.out.println(omdBDTO.getTitle());

		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
