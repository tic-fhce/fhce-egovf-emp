package com.fhce.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FhceEgovfEmpApplication /*extends SpringBootServletInitializer*/{
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FhceEgovfEmpApplication.class);
	}*//* para produccion*/

	public static void main(String[] args) {
		SpringApplication.run(FhceEgovfEmpApplication.class, args);
	}

}
