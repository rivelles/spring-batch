package com.example.springbatch.examples.writers;

import com.example.springbatch.examples.models.ClienteValidacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraArquivoValidacaoWriterConfiguration {

    @Bean
    ItemWriter<ClienteValidacao> leituraArquivoValidacaoWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
