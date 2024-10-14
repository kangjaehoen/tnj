package com.example.tnj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan
public class TnjApplication {

	public static void main(String[] args) {
		SpringApplication.run(TnjApplication.class, args);
	}

}
