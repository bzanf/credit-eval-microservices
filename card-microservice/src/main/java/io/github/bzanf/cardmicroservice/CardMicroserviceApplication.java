package io.github.bzanf.cardmicroservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CardMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardMicroserviceApplication.class, args);
	}

}
