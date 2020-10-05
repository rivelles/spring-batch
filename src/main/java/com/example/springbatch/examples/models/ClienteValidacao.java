package com.example.springbatch.examples.models;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ClienteValidacao {

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome deve ser alfabético")
    String nome;

    @NotNull
    @Range(min = 18, max = 200)
    String idade;

    @NotNull
    @Size(min = 1, max = 50)
    //@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "E-mail deve ser válido")
    String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClienteValidacao{" +
                "nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
