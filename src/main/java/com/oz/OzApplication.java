package com.oz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Humans",
					contact = @Contact(
						      name = "Adrian De Cello Salgado",
						      email = "adriansalgado215@gmail.com")))
public class OzApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzApplication.class, args);
	}

}
