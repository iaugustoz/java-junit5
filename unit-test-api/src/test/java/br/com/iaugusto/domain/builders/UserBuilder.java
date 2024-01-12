package br.com.iaugusto.domain.builders;

import br.com.iaugusto.domain.User;
import org.junit.jupiter.api.DisplayName;

public class UserBuilder {
    private Long id;
    private String name;
    private String email;
    private String password;

    private UserBuilder() {}

    public static UserBuilder umUsuario() {
        UserBuilder builder = new UserBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(UserBuilder builder) {
        builder.id = 1L;
        builder.name = "Usuário Válido";
        builder.email = "user@mail.com";
        builder.password = "123456789";
    }

    public UserBuilder comId(Long param) {
        id = param;
        return this;
    }

    public UserBuilder comNome(String param) {
        name = param;
        return this;
    }

    public UserBuilder comEmail(String param) {
        email = param;
        return this;
    }

    public UserBuilder comSenha(String param) {
        password = param;
        return this;
    }

    public User agora() {
        return new User(id, name, email, password);
    }
}
