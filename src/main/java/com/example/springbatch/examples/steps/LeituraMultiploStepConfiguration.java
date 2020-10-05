package com.example.springbatch.examples.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraMultiploStepConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step leituraMultiploStep(ItemReader leituraMultiploReader,
                                    ItemWriter leituraMultiploWriter) {
        return stepBuilderFactory.get("leituraMultiploStep")
                .chunk(1)
                .reader(leituraMultiploReader)
                .writer(leituraMultiploWriter)
                .build();
    }
}
