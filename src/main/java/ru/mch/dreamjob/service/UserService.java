package ru.mch.dreamjob.service;

import ru.mch.dreamjob.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);
}