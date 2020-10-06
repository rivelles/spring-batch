package com.example.springbatch.projeto.jobs.listeners;

import com.example.springbatch.SpringBatchApplication;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class FaturaJobListener {

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Starting execution");
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Ended execution");
    }
}
