package com.example.springbatch.examples.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraTamanhoFixoConfiguration {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    Step leituraArquivoFixoStep(ItemReader leituraArquivoFixoReader,
                                ItemWriter leituraArquivoFixoWriter) {
        return stepBuilderFactory.get("leituraArquivoFixoStep")
                .chunk(1)
                .reader(leituraArquivoFixoReader)
                .writer(leituraArquivoFixoWriter)
                .build();
    }
}