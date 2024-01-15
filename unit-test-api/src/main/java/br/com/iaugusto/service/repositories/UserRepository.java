package br.com.iaugusto.service.repositories;

import br.com.iaugusto.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> getUserByEmail(String email);
}
