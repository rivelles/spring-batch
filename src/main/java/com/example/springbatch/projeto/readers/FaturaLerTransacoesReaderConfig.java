package com.example.springbatch.projeto.readers;

import com.example.springbatch.projeto.models.CartaoCredito;
import com.example.springbatch.projeto.models.Cliente;
import com.example.springbatch.projeto.models.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FaturaLerTransacoesReaderConfig {

    @Bean
    public JdbcCursorItemReader<Transacao> faturaReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transacao>().name("faturaReader")
                .dataSource(dataSource)
                .sql("select * from transacao join cartao_credito using (numero_cartao_credito) order by numero_cartao_credito")
                .rowMapper((resultSet, i) -> {
                    Cliente cliente = new Cliente();
                    cliente.setId(resultSet.getInt("cliente"));

                    CartaoCredito cartaoCredito = new CartaoCredito();
                    cartaoCredito.setNumeroCartaoCredito(resultSet.getInt("numero_cartao_credito"));
                    cartaoCredito.setCliente(cliente);

                    Transacao transacao = new Transacao();
                    transacao.setId(resultSet.getInt("id"));
                    transacao.setCartaoCredito(cartaoCredito);
                    transacao.setData(resultSet.getDate("data"));
                    transacao.setDescricao(resultSet.getString("descricao"));
                    transacao.setValor(resultSet.getDouble("valor"));

                    return transacao;
                })
                .build();

    }
}
