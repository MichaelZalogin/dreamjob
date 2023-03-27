package ru.mch.dreamjob.repository.database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.mch.dreamjob.configuration.DatasourceConfiguration;
import ru.mch.dreamjob.entity.User;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var user = sql2oUserRepository.save(new User(0, "email", "password")).get();
        var savedUser = sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenSaveNotUniqueEmail() {
        User user1 = new User(1, "email1", "password");
        sql2oUserRepository.save(user1);
        User user2 = new User(2, "email1", "password");
        Assertions.assertThrows(Exception.class, () -> {
            sql2oUserRepository.save(user2);
        });
    }


//    public String loginUser(@ModelAttribute User user, Model model) {
//        var userOptional = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
//        if (userOptional.isEmpty()) {
//            model.addAttribute("error", "Почта или пароль введены неверно");
//            return "users/login";
//        }
//        return "redirect:/vacancies";
//    }

    @Test
    public void whenSaveNotUniqueEmail2() {
        User user1 = new User(1, "email1", "password");
        sql2oUserRepository.save(user1);
        User user2 = new User(2, "email1", "password");
        try {
            sql2oUserRepository.save(user2);
        } catch (Exception e) {
            assertEquals("Пользователь с этой электронной почтой уже существует."
                         + " Воспользуйтесь окном входа в систему", e.getMessage());
            throw e;
        }
    }

    @Test
    public void whenUserDoesNotRegistration() {
        User user = new User(1, "email1@mail.ru", "password");
        try {
            sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            assertEquals("Почта или пароль введены неверно", e.getMessage());
            throw e;
        }
    }
}