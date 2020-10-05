package com.example.springbatch.projeto.steps;

import com.example.springbatch.projeto.models.FaturaCartaoCredito;
import com.example.springbatch.projeto.models.Transacao;
import com.example.springbatch.projeto.readers.FaturaCartaoCreditoReader;
import com.example.springbatch.projeto.writers.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step faturaCartaoCreditoStep(ItemStreamReader<Transacao> faturaReader,
                                        ItemProcessor faturaProcessor,
                                        ItemWriter faturaWriter,
                                        TotalTransacoesFooterCallback totalTransacoesFooterCallback) {
        return stepBuilderFactory.get("faturaCartaoCreditoStep")
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1)
                .reader(new FaturaCartaoCreditoReader(faturaReader))
                .processor(faturaProcessor)
                .writer(faturaWriter)
                .listener(totalTransacoesFooterCallback) // Callback do footer deve ser um listener, pois ele precisa receber os valores das faturas
                .build();
    }
}
