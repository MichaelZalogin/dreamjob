package ru.mch.dreamjob.repository.database;

import org.springframework.stereotype.Repository;
import ru.mch.dreamjob.entity.User;
import ru.mch.dreamjob.repository.UserRepository;

import java.util.Optional;

@Repository
public class Sql2oUserRepository implements UserRepository {

    @Override
    public Optional<User> save(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.empty();
    }
}