package com.example.springbatch.examples.steps;

import com.example.springbatch.examples.readers.LeituraArquivoMultiplosFormatosReaderConfiguration;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraMultiplosFormatosStepConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step leituraMultiplosFormatos(FlatFileItemReader leituraMultiploReader,
                                         ItemWriter leituraMultiplosFormatosWriter) {
        return stepBuilderFactory.get("leituraMultiplosFormatos")
                .chunk(1)
                .reader(new LeituraArquivoMultiplosFormatosReaderConfiguration(leituraMultiploReader))
                .writer(leituraMultiplosFormatosWriter)
                .build();
    }
}
