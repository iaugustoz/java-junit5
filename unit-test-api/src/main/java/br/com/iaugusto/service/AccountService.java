package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.events.AccountEvent;
import br.com.iaugusto.service.events.AccountEvent.EventType;
import br.com.iaugusto.service.repositories.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository repository;

    private AccountEvent event;

    public AccountService(AccountRepository accountRepository, AccountEvent event) {
        this.repository = accountRepository;
        this.event = event;
    }

    public Account save(Account account) {
        List<Account> accounts = repository.getAccountByUser(account.getUser().getId());
        accounts.stream().forEach(existingAccount -> {
            if (account.getName().equalsIgnoreCase(existingAccount.getName())) {
                throw new ValidationException("Usuário já possui uma conta com este nome!");
            }
        });

        Account persistedAccount = repository.save(account);
        try {
            event.dispatch(persistedAccount, EventType.CREATED);
        } catch (Exception e) {
            repository.delete(persistedAccount);
            throw new RuntimeException("Falha na criação da conta, tente novamente.");
        }
        return persistedAccount;
    }
}
