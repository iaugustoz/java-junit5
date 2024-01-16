package br.com.iaugusto.infra;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.UserService;
import org.junit.jupiter.api.*;

import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceComUserMemoryRepositoryTest {
    private static UserService service = new UserService(new UserMemoryRepository());

    @Test
    @Order(1)
    @DisplayName("Deve salvar um usuário válido")
    void mustSaveValidUser() {
        User user = service.save(umUsuario().comId(null).agora());
        assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Deve rejeitar um usuário existente")
    void mustRejectExistingUser() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.save(umUsuario().comId(null).agora())
        );
        assertEquals("Usuário user@mail.com já cadastrado!", exception.getMessage());
    }
}
