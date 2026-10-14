package br.com.facasuadieta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FacasuadietaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacasuadietaApplication.class, args);
	}

}
