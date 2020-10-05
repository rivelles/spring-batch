package com.example.springbatch.projeto.writers;

import com.example.springbatch.projeto.models.FaturaCartaoCredito;
import io.micrometer.core.instrument.Metrics;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {

    private Double total = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.write(String.format("\n%121s", "Total: "+ NumberFormat.getCurrencyInstance().format(total)));
    }

    @BeforeWrite
    void beforeWrite(List<FaturaCartaoCredito> faturaCartaoCreditoList) {
        Iterator<FaturaCartaoCredito> iterator = faturaCartaoCreditoList.iterator();
        while (iterator.hasNext()) {
            FaturaCartaoCredito faturaCartaoCredito = iterator.next();
            total += faturaCartaoCredito.getTotal();
        }
    }

    @AfterChunk
    void afterChunk() {
        total = 0.0;
    }
}
