package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;

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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
