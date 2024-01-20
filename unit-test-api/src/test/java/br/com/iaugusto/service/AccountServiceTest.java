package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.events.AccountEvent;
import br.com.iaugusto.service.events.AccountEvent.EventType;
import br.com.iaugusto.service.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static br.com.iaugusto.domain.builders.AccountBuilder.umaAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService service;

    @Mock
    private AccountEvent event;

    @Mock
    private AccountRepository repository;

    @Test
    @DisplayName("Deve salvar primeira conta com sucesso")
    void mustSaveFirstAccountWithSuccessfully() throws Exception {
        Account accountToSave = umaAccount().comId(null).agora();

        when(repository.save(accountToSave)).thenReturn(umaAccount().agora());
        doNothing().when(event).dispatch(umaAccount().agora(), EventType.CREATED);

        Account savedAccount = service.save(accountToSave);

        assertNotNull(savedAccount.getId());
        verify(repository).save(accountToSave);
    }

    @Test
    @DisplayName("Deve salvar segunda conta mesmo já existindo outras")
    void mustSaveSecondAccountEveIfOthersAlreadyExist() {
        Account accountToSave = umaAccount().comId(null).agora();

        when(repository.getAccountByUser(accountToSave.getUser().getId()))
                .thenReturn(Arrays.asList(umaAccount().comName("Outra conta").agora()));
        when(repository.save(accountToSave)).thenReturn(umaAccount().agora());

        Account savedAccount = service.save(accountToSave);
        assertNotNull(savedAccount.getId());
    }

    @Test
    @DisplayName("Deve rejeitar conta repetida")
    void mustRejectRepeatedAccount() {
        Account accountToSave = umaAccount().comId(null).agora();

        when(repository.getAccountByUser(accountToSave.getUser().getId()))
                .thenReturn(Arrays.asList(umaAccount().agora()));
        //when(accountRepository.save(accountToSave)).thenReturn(umaAccount().agora());

        String message = assertThrows(ValidationException.class,
                () -> service.save(accountToSave)
        ).getMessage();
        assertEquals("Usuário já possui uma conta com este nome!", message);
    }

    @Test
    @DisplayName("Não deve manter conta sem evento")
    void mustNotMaintainAccountWithoutEvent() throws Exception {
        Account accountToSave = umaAccount().comId(null).agora();
        Account savedAccount = umaAccount().agora();

        when(repository.save(accountToSave)).thenReturn(savedAccount);
        doThrow(new Exception("Falha catastrófica")).when(event)
                .dispatch(savedAccount, EventType.CREATED);


        String message = assertThrows(Exception.class,
                () -> service.save(accountToSave)
        ).getMessage();
        assertEquals("Falha na criação da conta, tente novamente.", message);

        verify(repository).delete(savedAccount);
    }
}

