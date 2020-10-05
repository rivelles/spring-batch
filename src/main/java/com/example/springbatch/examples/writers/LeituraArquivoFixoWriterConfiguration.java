package com.example.springbatch.examples.writers;

import com.example.springbatch.examples.models.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoFixoWriterConfiguration {

    @StepScope
    @Bean
    FlatFileItemWriter<Cliente> leituraArquivoFixoWriter(@Value("#{jobParameters['arquivoClientesFixoSaida']}") Resource resource) {
        return new FlatFileItemWriterBuilder<Cliente>().name("leituraArquivoFixoWriter")
                .resource(resource)
                .formatted()
                .format("%-9s %-9s %-2s, %-19s")
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }
}
