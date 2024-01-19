package br.com.iaugusto.service.repositories;

import br.com.iaugusto.domain.Account;

public interface AccountRepository {
    Account save(Account account);
}
