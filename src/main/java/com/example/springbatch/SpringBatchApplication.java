package com.example.springbatch;

import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.springbatch.projeto", "com.example.springbatch.config"})
public class SpringBatchApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBatchApplication.class, args);

	}

}
