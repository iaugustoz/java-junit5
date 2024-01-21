package br.com.iaugusto.service.repositories;

import br.com.iaugusto.domain.Transaction;

public interface TransactionDao {
    Transaction salvar(Transaction transaction);
}
