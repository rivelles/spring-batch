package com.example.springbatch.examples.readers;

import com.example.springbatch.examples.models.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraMultiploReaderConfiguration {

    @StepScope
    @Bean
    public FlatFileItemReader leituraMultiploReader(
            @Value("#{jobParameters['arquivoClientesMultiplo']}") Resource arquivo,
            LineMapper lineMapperMultiplo) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraMultiploReader")
                .resource(arquivo)
                .lineMapper(lineMapperMultiplo)
                .build();
    }
}
