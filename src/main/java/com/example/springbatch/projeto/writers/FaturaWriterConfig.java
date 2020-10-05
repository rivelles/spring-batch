package com.example.springbatch.projeto.writers;

import com.example.springbatch.projeto.models.FaturaCartaoCredito;
import com.example.springbatch.projeto.models.Transacao;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

@Configuration
public class FaturaWriterConfig {

    @Bean
    MultiResourceItemWriter<FaturaCartaoCredito> faturaWriter() {
        return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>().name("faturaWriter")
                .resource(new FileSystemResource("files/fatura"))
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(i -> i+".txt")
                .delegate(criarArquivoFatura())
                .build();
    }

    private FlatFileItemWriter<FaturaCartaoCredito> criarArquivoFatura() {
        return new FlatFileItemWriterBuilder<FaturaCartaoCredito>().name("arquivoFatura")
                .resource(new FileSystemResource("files/fatura.txt"))
                .lineAggregator(faturaCartaoCredito -> {
                    StringBuilder writer = new StringBuilder();
                    writer.append(String.format("Nome: %s\n", faturaCartaoCredito.getCliente().getNome()));
                    writer.append(String.format("Endereço: %s\n", faturaCartaoCredito.getCliente().getEndereco()));
                    writer.append(String.format("Fatura do cartão %d\n", faturaCartaoCredito.getCartaoCredito().getNumeroCartaoCredito()));
                    writer.append("----------------------------------------------------------------------\n");
                    writer.append("DATA DESCRIÇÃO VALOR");
                    writer.append("----------------------------------------------------------------------\n");
                    Iterator<Transacao> iterator = faturaCartaoCredito.getTransacoes().iterator();
                    while (iterator.hasNext()) {
                        Transacao transacao = iterator.next();
                        writer.append(String.format("\n[%10s] %-80s - %s",
                                new SimpleDateFormat("dd/MM/yyyy").format(transacao.getData()),
                                transacao.getDescricao(),
                                NumberFormat.getCurrencyInstance().format(transacao.getValor())));
                    }

                    return writer.toString();
                })
                .headerCallback(writer -> {
                    writer.append(String.format("%121s\n", "Cartão XPTO"));
                    writer.append(String.format("%121s\n\n", "Rua Vergueiro, 131"));
                })
                .footerCallback(totalTransacoesFooterCallback())
                .build();
    }

    @Bean
    public FlatFileFooterCallback totalTransacoesFooterCallback() {
        return new TotalTransacoesFooterCallback();
    }
}
