package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;
    private String passcword;

    public User(Long id, String name, String email, String passcword) {
        if (name == null) {
            throw new ValidationException("O campo name é obrigatório");
        }

        if (email == null) {
            throw new ValidationException("O campo e-mail é obrigatório");
        }

        if (passcword == null) {
            throw new ValidationException("O campo password é obrigatório");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.passcword = passcword;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasscword() {
        return passcword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(passcword, user.passcword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, passcword);
    }
}
