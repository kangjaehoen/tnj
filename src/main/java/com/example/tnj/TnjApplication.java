package com.example.tnj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.tnj","mybatis.dao"})
@MapperScan(value={"mybatis.dao"})
public class TnjApplication {

	public static void main(String[] args) {
		SpringApplication.run(TnjApplication.class, args);
	}

}
