package com.example.springbatch.examples.readers;

import com.example.springbatch.examples.models.Cliente;
import com.example.springbatch.examples.models.Transacao;
import org.springframework.batch.item.*;

public class LeituraArquivoMultiplosFormatosReaderConfiguration implements ItemStreamReader<Cliente> {
    private Object objetoAtual;
    private ItemStreamReader<Object> delegate;

    public LeituraArquivoMultiplosFormatosReaderConfiguration(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (objetoAtual == null) objetoAtual = delegate.read(); //ler objeto

        Cliente cliente = (Cliente) objetoAtual;
        objetoAtual = null;

        if (cliente != null) {
            while (peek() instanceof Transacao)
                cliente.getTransacoes().add((Transacao) objetoAtual);
        }

        return cliente;
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

    private Object peek() throws Exception {
        objetoAtual = delegate.read();

        return objetoAtual;
    }
}
