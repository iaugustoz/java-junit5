package br.com.iaugusto.service;

import br.com.iaugusto.domain.Transaction;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.events.ClockService;
import br.com.iaugusto.service.repositories.TransactionDao;

import java.time.LocalDateTime;

public class TransactionService {
    private TransactionDao dao;
    private ClockService clock;

    public Transaction saveTransaction(Transaction transaction) {
        if (clock.getCurrentTime().getHour() > 19) {
            throw new RuntimeException("O horário ultrapassa o limite permitido. Tente amanhã.");
        }

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
