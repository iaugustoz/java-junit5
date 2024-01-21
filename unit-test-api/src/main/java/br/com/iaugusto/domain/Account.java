package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
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
}
