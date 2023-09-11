package com.api.usersintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.api.usersintegration", "com.api.usersintegration.mapper"})
@EntityScan("com.api.usersintegration.model")
public class UsersintegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersintegrationApplication.class, args);
	}

}
