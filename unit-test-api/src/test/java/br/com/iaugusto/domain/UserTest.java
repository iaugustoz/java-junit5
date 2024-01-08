package br.com.iaugusto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domínio: Usuário")
class UserTest {

    @Test
    @DisplayName("Deve criar um usuário válido com sucesso")
    void mustCreateValidUser() {
        User user = new User(1L, "Usuário Válido","user@gmail.com", "123456");

        // Collection de assertivas
        assertAll(
                "Usuário - Coleção de Assertivas",
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("Usuário Válido", user.getName()),
                () -> assertEquals("user@gmail.com", user.getEmail()),
                () -> assertEquals("123456", user.getPasscword())
        );
    }

}
