package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.service.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.iaugusto.domain.builders.AccountBuilder.umaAccount;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Deve salvar uma conta com sucesso")
    void mustSaveAccountWithSuccessfully() {
        Account accountToSave = umaAccount().comId(null).agora();

        when(accountRepository.save(accountToSave)).thenReturn(umaAccount().agora());

        Account savedAccount = accountService.save(accountToSave);

        assertNotNull(savedAccount.getId());
        verify(accountRepository).save(accountToSave);
    }
}

