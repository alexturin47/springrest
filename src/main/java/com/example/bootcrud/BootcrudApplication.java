package com.example.bootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "com.example.bootcrud" }, exclude = { ErrorMvcAutoConfiguration.class })
public class BootcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcrudApplication.class, args);
	}
//test 2st branch
}
