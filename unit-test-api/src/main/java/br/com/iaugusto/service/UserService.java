package br.com.iaugusto.service;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.domain.exceptions.ValidationException;
import br.com.iaugusto.service.repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        userRepository.getUserByEmail(user.getEmail()).ifPresent(u -> {
            throw new ValidationException(String.format("Usuário %s já cadastrado!", user.getEmail()));
        });
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}
