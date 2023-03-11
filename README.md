# Проект "Dream Job"

* [Описание](#описание)
* [Функционал](#функционал)
* [Технологии](#технологии)
* [Интерфейс](#интерфейс)
* [Автор](#автор)

## Описание
CRUD-MVC приложение на сервлетах и JSP, реализующее биржу
вакансий и кандидатов.
Можно добавлять/изменять данные по каждой вакансии и кандидату.
По кандидатам также поддерживается хранение фотографий профиля.

## Функционал
* Регистрация пользователя
* Аутентификация на сервлет-фильтрах
* Авторизация через БД PostgreSQL
* Добавление/изменение вакансий
* Добавление/изменение соискателей
* Добавление/изменение/скачивание фотографии соискателя
* Две модели хранения данных MemStore PsqlStore

## Технологии
* Java 18
* JDBC
* PostgreSQL
* Servlet&JSP&JSTL
* HTML, CSS, BOOTSTRAP, JS, AJAX, JQUERY
* Apache Tomcat Server
* Junit, Mockito, Powermock
* Log4j
* Apache Commons Fileupload

## Интерфейс

* Добавление кандидата
  ![ScreenShot](screenshots/addCandidate.PNG)

* Добавление вакансии
  ![ScreenShot](screenshots/addPost.PNG)

* Список кандидатов
  ![ScreenShot](screenshots/candidates.PNG)

* Список вакансий
  ![ScreenShot](screenshots/posts.PNG)

* Авторизация
  ![ScreenShot](screenshots/login.PNG)

* Регистрация
  ![ScreenShot](screenshots/registration.PNG)

* Проверка подлиности пароля
  ![ScreenShot](images/wrongPass.PNG)

## Автор

Michael Zalogin

zaloginmiha@gmail.com

+79111480757

[![Build Status](https://travis-ci.com/RamonOga/dream_job.svg?branch=master)](https://travis-ci.com/RamonOga/Dream_Job)