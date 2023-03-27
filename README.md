# Проект "Dream Job" (В процессе разработки)

* [Описание](#описание)
* [Функционал](#функционал)
* [Технологии](#технологии)
* [Интерфейс](#интерфейс)
* [Автор](#автор)

## Описание

MVC приложение на Spring Boot и Thymeleaf, реализующее биржу
вакансий и кандидатов.

## Функционал

* Регистрация пользователя;
* Аутентификация;
* Авторизация через БД PostgreSQL;
* Добавление/изменение/удаление вакансий;
* Добавление/изменение/удаление соискателей;
* Возможность работы с фотографиями профилей;
* Две модели хранения данных:
  - Хранение в оперативной памяти (реализация репозитория Memory#Repository);
  - Хранение в базе данных PostgreSQL (реализация репозитория Sql2o#Repository).

## Технологии

* Java 18;
* Spring boot;
* PostgreSQL;
* Sql2o (ORM) + commons-dbcp2(connection pool);
* Apache Tomcat Server;
* Thymeleaf;
* HTML, CSS, Bootstrap;
* Log4j;
* Тесты: Junit5, Mockito. Работают c H2 db;
* Maven. Поддержка профилей test/prod;
* Liquibase.

## Интерфейс

* Добавление кандидата
  ![Screenshot](screenshots/addCandidate.png)

* Добавление вакансии
  ![Screenshot](screenshots/addVacancy.png)

* Редактирование вакансии
  ![Screenshot](screenshots/editVacancy.png)

* Список вакансий
  ![Screenshot](screenshots/foreachVacancy.png)

* Регистрация
  ![Screenshot](screenshots/regUser.png)

* Авторизация
  ![Screenshot](screenshots/auth.png)

* Проверка подлиности пароля
  ![Screenshot](screenshots/errorAuth.png)

## Автор

Michael Zalogin

zaloginmiha@gmail.com

+79111480757

[![Build Status](https://travis-ci.com/RamonOga/dream_job.svg?branch=master)](https://travis-ci.com/RamonOga/Dream_Job)