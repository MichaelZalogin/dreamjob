package ru.mch.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.mch.dreamjob.entity.User;
import ru.mch.dreamjob.repository.UserRepository;

import java.util.Optional;

@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    public SimpleUserService(UserRepository sql2oUserRepository) {
        this.userRepository = sql2oUserRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}