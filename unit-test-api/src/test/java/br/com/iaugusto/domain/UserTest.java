package br.com.iaugusto.domain;

import br.com.iaugusto.domain.exceptions.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "[{index}] - {4}")
    @DisplayName("Deve validar campos obrigatórios")
    @CsvFileSource(
            files = "src\\test\\resources\\camposObrigatoriosUsuario.csv",
            nullValues = "NULL",
            numLinesToSkip = 1
    )
    void mustValidateMandatoryFields(Long id, String nome, String email, String senha, String expectedMessage) {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora()
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}
