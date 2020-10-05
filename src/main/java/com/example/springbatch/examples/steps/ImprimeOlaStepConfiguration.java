package com.example.springbatch.examples.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeOlaStepConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    Step imprimeOlaStep(Tasklet imprimeOlaTasklet) {
        return stepBuilderFactory
                .get("imprimeOlaStep")
                .tasklet(imprimeOlaTasklet)
                .build();
    }
}
