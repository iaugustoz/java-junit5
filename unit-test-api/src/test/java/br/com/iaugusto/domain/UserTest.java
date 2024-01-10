package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domínio: Usuário")
class UserTest {

    @Test
    @DisplayName("Deve criar um usuário válido com sucesso")
    void mustCreateValidUser() {
        User user = umUsuario().agora();
        // Collection de assertivas
        assertAll(
                "Usuário - Coleção de Assertivas",
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("Usuário Válido", user.getName()),
                () -> assertEquals("user@mail.com", user.getEmail()),
                () -> assertEquals("123456789", user.getPasscword())
        );
    }

    @Test
    @DisplayName("Deve rejeitar um usuário sem nome")
    void mustRejectUnnamedUser() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> umUsuario().comNome(null).agora()
        );

        assertEquals("O campo name é obrigatório!", exception.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar um usuário sem e-mail")
    void mustRejectUserWithoutEmail() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> umUsuario().comEmail(null).agora()
        );

        assertEquals("O campo e-mail é obrigatório!", exception.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar um usuário sem senha")
    void mustRejectUserWithoutPassword() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> umUsuario().comSenha(null).agora()
        );

        assertEquals("O campo password é obrigatório!", exception.getMessage());
    }
}
