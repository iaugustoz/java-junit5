package br.com.iaugusto.service.events;

import br.com.iaugusto.domain.Account;

public interface AccountEvent {
    enum EventType {
        CREATED,
        UPDATED,
        DELETED
    }

    void dispatch(Account account, EventType type) throws Exception;
}
