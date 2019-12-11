package com.mahaboob.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CurrencyConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}
	
}
