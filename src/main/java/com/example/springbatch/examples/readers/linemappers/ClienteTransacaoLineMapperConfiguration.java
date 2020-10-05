package com.example.springbatch.examples.readers.linemappers;

import com.example.springbatch.examples.models.Cliente;
import com.example.springbatch.examples.models.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapperConfiguration {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapperMultiplo() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());

        return lineMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer>  tokenizerMap = new HashMap<String, LineTokenizer>();
        tokenizerMap.put("0*", clienteLineTokenizer());
        tokenizerMap.put("1*", transacaoLineTokenizer());

        return tokenizerMap;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome", "sobrenome", "idade", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);

        return lineTokenizer;
    }

    private LineTokenizer transacaoLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "descricao", "valor");
        lineTokenizer.setIncludedFields(1, 2, 3);

        return lineTokenizer;
    }

    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMapperMap = new HashMap<String, FieldSetMapper>();
        fieldSetMapperMap.put("0*", createFieldSetMapper(Cliente.class));
        fieldSetMapperMap.put("1*", createFieldSetMapper(Transacao.class));

        return fieldSetMapperMap;
    }

    private FieldSetMapper createFieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(classe);

        return fieldSetMapper;
    }
}
