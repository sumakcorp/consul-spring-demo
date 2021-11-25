package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan("com.example.demo")
public class ConsulDemoApplication {
	@Value("${security.codeconnect.client.tokenURL}")
	private String tokenURL;
	public static void main(String[] args) {
		SpringApplication.run(ConsulDemoApplication.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println(
				"********** DemoApplication >> security.codeconnect.tokenURL: " + tokenURL);
	}
}
