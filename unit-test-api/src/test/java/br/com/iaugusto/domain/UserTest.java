package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("Deve rejeitar um usuário sem nome")
    void mustRejectUnnamedUser() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new User(2L, null, "user2@gmail.com", "7890")
        );

        assertEquals("O campo name é obrigatório!", exception.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar um usuário sem e-mail")
    void mustRejectUserWithoutEmail() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new User(3L, "Test User", null, "123")
        );

        assertEquals("O campo e-mail é obrigatório!", exception.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar um usuário sem senha")
    void mustRejectUserWithoutPassword() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new User(4L, "User Usuário", "user3@gmail.com", null)
        );

        assertEquals("O campo password é obrigatório!", exception.getMessage());
    }
}
