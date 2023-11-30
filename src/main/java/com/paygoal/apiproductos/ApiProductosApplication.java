package com.paygoal.apiproductos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiProductosApplication {
	@Bean
	public ModelMapper modelMapper(){ //crea y devuelve instancias de modelMapper
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiProductosApplication.class, args);
	}

}
