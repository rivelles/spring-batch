package com.example.springbatch.examples.processors;

import com.example.springbatch.examples.models.ClienteValidacao;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
/*
    Criação de processador contendo uma composição de dois validadores separados por métodos e retornados dentro do bean
 */

@Configuration
public class ArquivoValidacaoProcessorConfiguration {

    private Set<String> emails = new HashSet<>();

    @Bean
    ItemProcessor<ClienteValidacao, ClienteValidacao> arquivoValidacaoProcessor() throws Exception {
        return new CompositeItemProcessorBuilder<ClienteValidacao, ClienteValidacao>()
                .delegates(createBeanValidatingItemProcessor(),
                        createValidatingItemProcessor())
                .build();
    }

    private BeanValidatingItemProcessor createBeanValidatingItemProcessor() throws Exception {
        BeanValidatingItemProcessor<ClienteValidacao> processor = new BeanValidatingItemProcessor<ClienteValidacao>();
        processor.setFilter(true); // Não vai parar o job com erro, vai apenas filtrar as linhas inválidas
        processor.afterPropertiesSet(); // Seta as propriedades, usado quando usamos validadores compostos

        return processor;
    }

    private ValidatingItemProcessor createValidatingItemProcessor() {
        ValidatingItemProcessor<ClienteValidacao> processor = new ValidatingItemProcessor<>();
        processor.setValidator(clienteValidacao -> {
            if (emails.contains(clienteValidacao.getEmail())) {
                throw new ValidationException(String.format("O cliente %s já foi processado", clienteValidacao.getEmail()));
            }
            emails.add(clienteValidacao.getEmail());
        });
        processor.setFilter(true);

        return processor;
    }
}
