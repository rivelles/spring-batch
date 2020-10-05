package com.example.springbatch.examples.readers;

import com.example.springbatch.examples.models.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraDelimitadoReaderConfiguration {

    @StepScope
    @Bean
    FlatFileItemReader<Cliente> leituraArquivoDelimitado(@Value("#{jobParameters['arquivoClientesDelimitado']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitado")
                .resource(arquivo)
                .delimited()
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
