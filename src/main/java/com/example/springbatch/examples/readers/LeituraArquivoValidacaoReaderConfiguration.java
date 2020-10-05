package com.example.springbatch.examples.readers;

import com.example.springbatch.examples.models.ClienteValidacao;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraArquivoValidacaoReaderConfiguration {

    @StepScope
    @Bean
    FlatFileItemReader<ClienteValidacao> leituraArquivoValidacaoReader(@Value("#{jobParameters['arquivoClientesValidacao']}") Resource arquivo) {
        return new FlatFileItemReaderBuilder<ClienteValidacao>()
                .name("leituraArquivoValidacaoReader")
                .resource(arquivo)
                .delimited()
                .names("nome", "idade", "email")
                .targetType(ClienteValidacao.class)
                .build();
    }
}
