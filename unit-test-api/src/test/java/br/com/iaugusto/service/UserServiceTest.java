package br.com.iaugusto.service;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.iaugusto.domain.builders.UserBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @Test
    @DisplayName("Deve retornar usuário por e-mail")
    void mustReturnUserByEmail() {
        when(repository.getUserByEmail("mail@mail.com"))
                .thenReturn(Optional.of(umUsuario().comEmail("mail@mail.com").agora()));

        Optional<User> user = service.getUserByEmail("mail@mail.com");
        assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("Deve salvar o usuário com sucesso")
    void mustSaveUserWithSuccessfully() {
        User userToSave = umUsuario().comId(null).agora();

        when(repository.save(userToSave)).thenReturn(umUsuario().agora());

        User savedUser = service.save(userToSave);

        assertNotNull(savedUser.getId());
        verify(repository).getUserByEmail(userToSave.getEmail());
    }

    @Test
    @DisplayName("Deve rejeitar usuário existente")
    void mustRejectExistingUser() {
        User userToSave = umUsuario().comId(null).agora();
        when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.of(umUsuario().agora()));

        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.save(userToSave)
        );
        assertTrue(exception.getMessage().endsWith("já cadastrado!"));
        verify(repository, never()).save(userToSave);
    }
}
