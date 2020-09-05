package com.stackroute.springboot;

import com.stackroute.springboot.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}






}
