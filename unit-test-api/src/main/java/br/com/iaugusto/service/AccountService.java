package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.service.repositories.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
