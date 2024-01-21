package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Account {
    private Long id;
    private String name;
    private User user;

    public Account(Long id, String name, User user) {
        if (name == null) {
            throw new ValidationException("O campo name é obrigatório");
        }

        if (user == null) {
            throw new ValidationException("O campo user é obrigatório");
        }

        this.id = id;
        this.name = name;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user);
    }
}
