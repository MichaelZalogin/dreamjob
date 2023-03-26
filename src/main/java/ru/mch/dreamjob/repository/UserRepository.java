package ru.mch.dreamjob.repository;

import ru.mch.dreamjob.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

}