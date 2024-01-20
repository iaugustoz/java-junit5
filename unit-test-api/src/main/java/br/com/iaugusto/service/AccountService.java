package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.events.AccountEvent;
import br.com.iaugusto.service.events.AccountEvent.EventType;
import br.com.iaugusto.service.repositories.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;

    private AccountEvent accountEvent;

    public AccountService(AccountRepository accountRepository, AccountEvent event) {
        this.accountRepository = accountRepository;
        this.accountEvent = event;
    }

    public Account save(Account account) {
        List<Account> accounts = accountRepository.getAccountByUser(account.getUser().getId());
        accounts.stream().forEach(existingAccount -> {
            if (account.getName().equalsIgnoreCase(existingAccount.getName())) {
                throw new ValidationException("Usuário já possui uma conta com este nome!");
            }
        });
        Account persistedAccount = accountRepository.save(account);
        accountEvent.dispatch(persistedAccount, EventType.CREATED);
        return persistedAccount;
    }
}
