package com.amex.useremailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UseremailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseremailserviceApplication.class, args);
	}

}
