package com.example.springbatch.examples.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class ArquivoClientesValidacaoJobConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job leArquivoValidacao(Step leituraArquivoValidacao) {
        return jobBuilderFactory.get("leArquivoValidacao")
                .start(leituraArquivoValidacao)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
