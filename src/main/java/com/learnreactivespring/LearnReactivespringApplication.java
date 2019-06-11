package com.learnreactivespring;

import com.learnreactivespring.controller.nonReactiveController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnReactivespringApplication {
//Creating non blocking end to end Reactive Api
	public static void main(String[] args) {
		SpringApplication.run(LearnReactivespringApplication.class, args);
	}
		nonReactiveController nr;
}
