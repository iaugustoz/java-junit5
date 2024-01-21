package br.com.iaugusto.service;

import br.com.iaugusto.domain.Transaction;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.repositories.TransactionDao;

public class TransactionService {
    private TransactionDao dao;

    public Transaction saveTransaction(Transaction transaction) {
        if (transaction.getDescription() == null) {
            throw new ValidationException("Descrição inexistente!");
        }

        if (transaction.getValue() == null) {
            throw new ValidationException("Valor inexistente!");
        }

        if (transaction.getDate() == null) {
            throw new ValidationException("Data inexistente!");
        }

        if (transaction.getAccount() == null) {
            throw new ValidationException("Conta inexistente!");
        }

        if (transaction.getStatus() == null) {
            transaction.setStatus(false);
        }

        return dao.salvar(transaction);
    }
}
