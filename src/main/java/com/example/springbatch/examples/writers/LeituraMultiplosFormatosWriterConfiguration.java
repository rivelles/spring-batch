package com.example.springbatch.examples.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraMultiplosFormatosWriterConfiguration {

    @Bean
    ItemWriter<Object> leituraMultiplosFormatosWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
