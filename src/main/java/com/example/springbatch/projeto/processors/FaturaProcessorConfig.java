package com.example.springbatch.projeto.processors;

import com.example.springbatch.projeto.models.Cliente;
import com.example.springbatch.projeto.models.FaturaCartaoCredito;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FaturaProcessorConfig implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
        String uri = String.format("https://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d",
                faturaCartaoCredito.getCliente().getId());

        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ValidationException("Cliente não encontrado!");
        }

        faturaCartaoCredito.setCliente(response.getBody());

        return faturaCartaoCredito;
    }
}
