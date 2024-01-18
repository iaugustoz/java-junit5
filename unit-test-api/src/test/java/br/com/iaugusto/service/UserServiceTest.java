package br.com.iaugusto.service;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.service.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService service;

    @Test
    @DisplayName("Deve retornar usuário por e-mail")
    void mustReturnUserByEmail() {
        UserRepository repository = mock(UserRepository.class);
        service = new UserService(repository);

        when(repository.getUserByEmail("mail@mail.com"))
                .thenReturn(Optional.of(umUsuario().comEmail("mail@mail.com").agora()));

        Optional<User> user = service.getUserByEmail("mail@mail.com");
        assertTrue(user.isPresent());
    }
}
