package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.utils.FileStorageProperties;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties(
		FileStorageProperties.class
		)
public class DemoShopOnlineApplication {

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoShopOnlineApplication.class, args);
	}
	


}
