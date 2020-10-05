package com.example.springbatch.examples.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ImprimeOlaTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String nome = (String) chunkContext.getStepContext().getJobParameters().get("nome");
        System.out.println("Hello, "+nome);
        return RepeatStatus.FINISHED;
    }
}
