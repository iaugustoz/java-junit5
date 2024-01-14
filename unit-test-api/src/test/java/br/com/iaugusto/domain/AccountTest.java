package br.com.iaugusto.domain;

import br.com.iaugusto.domain.builders.AccountBuilder;
import br.com.iaugusto.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.com.iaugusto.domain.builders.AccountBuilder.umaAccount;
import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    @DisplayName("Deve criar uma conta válida")
    void mustCreateAccountValid() {
        Account conta = umaAccount().agora();
        assertAll(
                "Conta",
                () -> assertEquals(1L, conta.getId()),
                () -> assertEquals("Conta Válida", conta.getName()),
                () -> assertEquals(umUsuario().agora(), conta.getUser())
        );
    }

    @ParameterizedTest
    @DisplayName("Deve rejeitar uma conta inválida")
    @MethodSource("dataProvider")
    void mustRejectAccountInvalid(Long id, String name, User user, String expectedMessage) {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> umaAccount().comId(id).comName(name).comUser(user).agora()
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().agora(), "O campo name é obrigatório"),
                Arguments.of(1L, "Conta Válido", null, "O campo user é obrigatório")
        );
    }

}
