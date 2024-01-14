package br.com.iaugusto.domain;

public class Account {
    private Long id;
    private String name;
    private User user;

    public Account(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
