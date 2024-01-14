package br.com.iaugusto.domain.builders;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.domain.Account;

import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;

public class AccountBuilder {
    private Long id;
    private String name;
    private User user;

    private AccountBuilder(){}

    public static AccountBuilder umaAccount() {
        AccountBuilder builder = new AccountBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(AccountBuilder builder) {
        builder.id = 1L;
        builder.name = "Conta Válida";
        builder.user = umUsuario().agora();
    }

    public AccountBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder comName(String name) {
        this.name = name;
        return this;
    }

    public AccountBuilder comUser(User user) {
        this.user = user;
        return this;
    }

    public Account agora() {
        return new Account(id, name, user);
    }
}
