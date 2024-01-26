package br.com.iaugusto.service;

import br.com.iaugusto.domain.Account;
import br.com.iaugusto.domain.Transaction;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.events.ClockService;
import br.com.iaugusto.service.repositories.TransactionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static br.com.iaugusto.domain.builders.AccountBuilder.umaAccount;
import static br.com.iaugusto.domain.builders.TransactionBuilder.umaTransaction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionDao dao;

    @Mock
    private ClockService clock;

    @Captor
    private ArgumentCaptor<Transaction> captor;

    @BeforeEach
    void setUp() {
        when(clock.getCurrentTime()).thenReturn(LocalDateTime.of(2023, 1, 28, 10, 0, 0));
    }

    @Test
    @DisplayName("Deve salvar uma transação válida")
    void mustSaveValidTransaction() {
        Transaction transactionToSave = umaTransaction().comId(null).agora();

        when(dao.salvar(transactionToSave)).thenReturn(umaTransaction().agora());

        Transaction savedTransaction = service.saveTransaction(transactionToSave);

        assertNotNull(savedTransaction.getId());
    }

    @ParameterizedTest(name = "[{index}] - {4}")
    @DisplayName("Deve validar campos obrigatórios ao salvar")
    @MethodSource(value = "dataProvider")
    void mustValidateMandatoryFieldsWhenSaving(String description, Double value, LocalDate date, Account account, String expectedMessage) {
        Transaction transactionToSave = umaTransaction()
                .comDescription(description)
                .comValue(value)
                .comDate(date)
                .comAccount(account)
                .agora();

        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.saveTransaction(transactionToSave)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar uma transação que exceda o horário")
    void mustRejectTransactionThatExceedsTime() {
        when(clock.getCurrentTime()).thenReturn(LocalDateTime.of(2023, 1, 28, 23, 0, 0));

        String exceptionMessage = assertThrows(ValidationException.class,
                () -> service.saveTransaction(umaTransaction().agora())
        ).getMessage();
        assertEquals("O horário ultrapassa o limite permitido. Tente amanhã.",  exceptionMessage);
    }

    @Test
    @DisplayName("Deve salvar transação pendente por padrão")
    void mustSavePendingTransactionByDefault() {
        Transaction transactionToSave = umaTransaction().comStatus(null).agora();
        service.saveTransaction(transactionToSave);

        verify(dao).salvar(captor.capture());
        Transaction transactioValided = captor.getValue();
        assertFalse(transactioValided.getStatus());
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(null, 10.0, LocalDate.now(), umaAccount().agora(), "Descrição inexistente!"),
                Arguments.of("Transação Válida", null, LocalDate.now(), umaAccount().agora(), "Valor inexistente!"),
                Arguments.of("Transação Válida", 10.0, null, umaAccount().agora(), "Data inexistente!"),
                Arguments.of("Transação Válida", 10.0, LocalDate.now(), null, "Conta inexistente!")
        );
    }
}
