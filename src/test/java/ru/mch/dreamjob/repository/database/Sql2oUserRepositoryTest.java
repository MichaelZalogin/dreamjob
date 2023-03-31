package ru.mch.dreamjob.repository.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.mch.dreamjob.configuration.DatasourceConfiguration;
import ru.mch.dreamjob.entity.User;

import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oVacancyRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        sql2o = configuration.databaseClient(datasource);
        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterAll
    public static void deleteFile() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                    DELETE FROM users
                    """).executeUpdate();
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var user = sql2oUserRepository.save(new User(0, "name", "email", "password")).get();
        var savedUser = sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenSaveNotUniqueEmail() {
        User user1 = new User(1, "name", "email1", "password");
        Optional<User> userOptional1 = sql2oUserRepository.save(user1);
        User user2 = new User(2, "name", "email1", "password");
        Optional<User> userOptional2 = sql2oUserRepository.save(user2);
        assertThat(userOptional1.get()).isEqualTo(user1);
        assertThat(userOptional2.isEmpty()).isTrue();
    }

    @Test
    public void whenUserDoesNotRegistration() {
        User user = new User(12, "name2", "email1@mail.ru", "password");
        Optional<User> userOptional = sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        assertThat(userOptional.isEmpty()).isTrue();
    }
}