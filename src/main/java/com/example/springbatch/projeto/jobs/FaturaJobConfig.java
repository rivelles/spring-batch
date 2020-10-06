package com.example.springbatch.projeto.jobs;

import com.example.springbatch.projeto.jobs.listeners.FaturaJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobListenerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class FaturaJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job faturaCartaoCreditoJob(Step faturaCartaoCreditoStep) {
        return jobBuilderFactory.get("faturaCartaoCreditoJob")
                .start(faturaCartaoCreditoStep)
                .incrementer(new RunIdIncrementer())
                .listener(JobListenerFactoryBean.getListener(new FaturaJobListener()))
                .build();
    }
}
