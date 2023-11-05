package com.aa.easyBag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan(basePackages = "com.aa.EasyBag")
public class EasyBagApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyBagApplication.class, args);
	}
}
