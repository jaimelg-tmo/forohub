package com.aluracursos.forohub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		// Cargar .env y promover a System properties
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));

		SpringApplication.run(ForohubApplication.class, args);
	}
}