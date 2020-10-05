package com.example.springbatch.examples.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStepConfiguration {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    Step imprimeParImparStep(ItemReader contaAteDezReader,
                             ItemProcessor parOuImparProcessor,
                             ItemWriter imprimeWriter) {
        return stepBuilderFactory.get("imprimeParImparStep")
                .<Integer, String>chunk(10)
                .reader(contaAteDezReader)
                .processor(parOuImparProcessor)
                .writer(imprimeWriter)
                .build();
    }
}
