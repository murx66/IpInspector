package com.meli.ipinspector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpInspectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpInspectorApplication.class, args);
	}

}
