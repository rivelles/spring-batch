package com.example.springbatch.examples.processors;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParOuImparProcessorConfiguration {

    @Bean
    FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> {
            if (item % 2 == 0) return item+" é par";
            else return item+" é ímpar";
        });
    }
}
