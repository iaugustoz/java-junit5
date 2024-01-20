package br.com.iaugusto.service.repositories;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.User;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);

    List<Account> getAccountByUser(Long userId);
}
