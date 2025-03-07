package com.example.simple_todo_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTodoListApplication.class, args);
	}

}
