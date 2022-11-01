package ru.team2.skud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SkudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkudApplication.class, args);
	}

}
