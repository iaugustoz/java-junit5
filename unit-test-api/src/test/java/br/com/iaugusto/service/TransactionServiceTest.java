package br.com.iaugusto.service;

import br.com.iaugusto.domain.Transaction;
import br.com.iaugusto.service.repositories.TransactionDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.iaugusto.domain.builders.TransactionBuilder.umaTransaction;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionDao dao;

    @Test
    @DisplayName("")
    void mustSaveValidTransaction() {
        Transaction transactionToSave = umaTransaction().comId(null).agora();

        when(dao.salvar(transactionToSave)).thenReturn(umaTransaction().agora());

        Transaction savedTransaction = service.saveTransaction(transactionToSave);

        assertNotNull(savedTransaction.getId());
    }
}
