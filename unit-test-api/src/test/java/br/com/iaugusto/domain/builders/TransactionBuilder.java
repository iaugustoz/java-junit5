package br.com.iaugusto.domain.builders;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.Transaction;

import java.time.LocalDate;

import static br.com.iaugusto.domain.builders.AccountBuilder.umaAccount;


public class TransactionBuilder {
    private Transaction elemento;

    private TransactionBuilder() {
    }

    public static TransactionBuilder umaTransaction() {
        TransactionBuilder builder = new TransactionBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(TransactionBuilder builder) {
        builder.elemento = new Transaction();
        Transaction elemento = builder.elemento;


        elemento.setId(1L);
        elemento.setDescription("Transação Válida");
        elemento.setValue(10.0);
        elemento.setAccount(umaAccount().agora());
        elemento.setDate(LocalDate.now());
        elemento.setStatus(false);
    }

    public TransactionBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public TransactionBuilder comDescription(String param) {
        elemento.setDescription(param);
        return this;
    }

    public TransactionBuilder comValue(Double param) {
        elemento.setValue(param);
        return this;
    }

    public TransactionBuilder comAccount(Account param) {
        elemento.setAccount(param);
        return this;
    }

    public TransactionBuilder comDate(LocalDate param) {
        elemento.setDate(param);
        return this;
    }

    public TransactionBuilder comStatus(Boolean param) {
        elemento.setStatus(param);
        return this;
    }

    public Transaction agora() {
        return elemento;
    }
}
