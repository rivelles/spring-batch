package com.example.springbatch.projeto.readers;

import com.example.springbatch.projeto.models.FaturaCartaoCredito;
import com.example.springbatch.projeto.models.Transacao;
import io.micrometer.core.instrument.Metrics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.*;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {

    private static final Logger logger = LogManager.getLogger(FaturaCartaoCreditoReader.class);

    private final ItemStreamReader<Transacao> delegate;
    private Transacao transacaoAtual;

    public FaturaCartaoCreditoReader(ItemStreamReader delegate) {
        this.delegate = delegate;
    }

    @Override
    public FaturaCartaoCredito read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (transacaoAtual == null) transacaoAtual = delegate.read();

        Transacao transacao = transacaoAtual;
        transacaoAtual = null;

        FaturaCartaoCredito faturaCartaoCredito = null;

        if (transacao != null) {
            faturaCartaoCredito = new FaturaCartaoCredito();
            faturaCartaoCredito.setCliente(transacao.getCartaoCredito().getCliente());
            faturaCartaoCredito.getTransacoes().add(transacao);
            faturaCartaoCredito.setCartaoCredito(transacao.getCartaoCredito());

            while (isTransacaoRelacionada(transacao)) {
                faturaCartaoCredito.getTransacoes().add(transacaoAtual);
            }
        }
        return faturaCartaoCredito;
    }

    private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
        return peek() != null && transacao.getCartaoCredito().getNumeroCartaoCredito() == transacaoAtual.getCartaoCredito().getNumeroCartaoCredito();
    }

    private Transacao peek() throws Exception {
        transacaoAtual = delegate.read();
        return transacaoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
