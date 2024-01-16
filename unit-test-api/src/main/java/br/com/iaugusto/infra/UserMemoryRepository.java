package br.com.iaugusto.infra;

import br.com.iaugusto.domain.User;
import br.com.iaugusto.service.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMemoryRepository implements UserRepository {
    // Armazena os usuários em memória
    private List<User> users;
    // Representa o id auto incrementável do BD
    private Long currentId;

    public UserMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        save(new User(null, "User #1", "user1@mail.com", "123456"));
    }

    @Override
    public User save(User user) {
        User newUser = new User(nextId(), user.getName(), user.getEmail(), user.getPasscword());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId() {
        return ++currentId;
    }
}
